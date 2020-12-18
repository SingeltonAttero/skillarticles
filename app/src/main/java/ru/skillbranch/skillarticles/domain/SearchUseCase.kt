package ru.skillbranch.skillarticles.domain

import io.reactivex.rxjava3.core.Observable
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import java.util.*

class SearchUseCase(private val repository: DishesRepositoryContract) {
    fun getDishes() = repository.getCachedDishes()
    fun findDishesByName(searchText: String): Observable<List<DishEntity>> = repository.getCachedDishes().toObservable()
        .map { dishes ->
            dishes.filter {
                it.title.toLowerCase(Locale.ROOT).contains(searchText.trim().toLowerCase(Locale.ROOT))
            }
        }
}