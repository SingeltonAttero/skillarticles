package ru.skillbranch.skillarticles.domain.filter

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity

interface CategoriesFilter {
    fun categoryFilterDishes(categoryId: String): Single<List<DishEntity>>
}