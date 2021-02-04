package ru.skillbranch.skillarticles.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.database.dao.DishesDao
import ru.skillbranch.skillarticles.repository.database.entity.DishPersistEntity
import ru.skillbranch.skillarticles.repository.http.DeliveryApi
import ru.skillbranch.skillarticles.repository.http.client.DeliveryRetrofitProvider
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper
import ru.skillbranch.skillarticles.repository.models.Category
import ru.skillbranch.skillarticles.repository.models.Dish
import ru.skillbranch.skillarticles.repository.models.RefreshToken

class DishesRepository(
    private val api: DeliveryApi,
    private val mapper: DishesMapper,
    private val dishesDao: DishesDao
) : DishesRepositoryContract {

    override fun getDishes(): Single<List<DishEntity>> =
        api.refreshToken(RefreshToken(DeliveryRetrofitProvider.REFRESH_TOKEN))
            .flatMap { api.getDishes(0, 1000, "${DeliveryRetrofitProvider.BEARER} ${it.accessToken}") }
            .doOnSuccess { dishes: List<Dish> ->
                val savePersistDishes: List<DishPersistEntity> = mapper.mapDtoToPersist(dishes)
                dishesDao.insertDishes(savePersistDishes)
            }
            .map { mapper.mapDtoToEntity(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getCachedDishes(): Single<List<DishEntity>> {
        return dishesDao.getAllDishes().map { mapper.mapPersistToEntity(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCategories(): Single<List<Category>> {
        return api.refreshToken(RefreshToken(DeliveryRetrofitProvider.REFRESH_TOKEN))
            .flatMap { api.getCategories(0, 1000, "${DeliveryRetrofitProvider.BEARER} ${it.accessToken}") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun findDishesByName(searchText: String): Observable<List<DishEntity>> {
        return dishesDao.findDishesFrom(searchText).map { mapper.mapPersistToEntity(it) }.toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}