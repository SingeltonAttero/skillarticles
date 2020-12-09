package ru.skillbranch.skillarticles.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.repository.models.Dish

interface DishesRepositoryContract {
    fun getDishes(): Single<List<Dish>>
    fun getCachedDishes(): Observable<List<Dish>>
}