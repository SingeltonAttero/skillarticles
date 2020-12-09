package ru.skillbranch.skillarticles.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import ru.skillbranch.skillarticles.repository.http.DeliveryApi
import ru.skillbranch.skillarticles.repository.http.client.DeliveryRetrofitProvider
import ru.skillbranch.skillarticles.repository.models.Dish
import ru.skillbranch.skillarticles.repository.models.RefreshToken

class DishesRepository(private val api: DeliveryApi) : DishesRepositoryContract {

    private val cachedDishes = ReplaySubject.create<List<Dish>>()

    override fun getDishes(): Single<List<Dish>> =
        api.refreshToken(RefreshToken(DeliveryRetrofitProvider.REFRESH_TOKEN))
            .flatMap { api.getDishes(0, 100, "${DeliveryRetrofitProvider.BEARER} ${it.accessToken}") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { dishes: List<Dish> ->
                cachedDishes.onNext(dishes)
            }


    override fun getCachedDishes(): Observable<List<Dish>> {
        return cachedDishes.hide()
    }
}