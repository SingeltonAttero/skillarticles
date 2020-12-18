package ru.skillbranch.skillarticles.repository

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity

interface DishesRepositoryContract {
    fun getDishes(): Single<List<DishEntity>>
    fun getCachedDishes(): Single<List<DishEntity>>
}