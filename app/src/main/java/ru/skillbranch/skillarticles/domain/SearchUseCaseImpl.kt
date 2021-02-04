package ru.skillbranch.skillarticles.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract

class SearchUseCaseImpl(private val repository: DishesRepositoryContract) : SearchUseCase {

    override fun getDishes(): Single<List<DishEntity>> = repository.getCachedDishes()


    override fun findDishesByName(searchText: String): Observable<List<DishEntity>> =
        repository.findDishesByName(searchText)
}