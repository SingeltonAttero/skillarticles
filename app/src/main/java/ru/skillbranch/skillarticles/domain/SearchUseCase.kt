package ru.skillbranch.skillarticles.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity

interface SearchUseCase {
    fun getDishes(): Single<List<DishEntity>>
    fun findDishesByName(searchText: String): Observable<List<DishEntity>>
}