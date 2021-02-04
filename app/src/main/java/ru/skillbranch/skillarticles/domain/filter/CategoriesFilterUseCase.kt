package ru.skillbranch.skillarticles.domain.filter

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.error.EmptyDishesError

class CategoriesFilterUseCase(private val repository: DishesRepositoryContract) : CategoriesFilter {
    override fun categoryFilterDishes(categoryId: String): Single<List<DishEntity>> {
        return repository.getCachedDishes().map { cacheList ->
            if (categoryId.isEmpty()) {
                cacheList
            } else {
                cacheList.filter { it.categoryId == categoryId }
            }
        }.flatMap {
            if (it.isEmpty()) {
                Single.error(EmptyDishesError("Not found dishes"))
            } else {
                Single.just(it)
            }
        }
    }
}