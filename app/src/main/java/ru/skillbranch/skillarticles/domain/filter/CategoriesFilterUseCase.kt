package ru.skillbranch.skillarticles.domain.filter

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract

class CategoriesFilterUseCase(private val repository: DishesRepositoryContract) : CategoriesFilter {
    override fun categoryFilterDishes(categoryId: String): Single<List<DishEntity>> {
        return TODO()
    }
}