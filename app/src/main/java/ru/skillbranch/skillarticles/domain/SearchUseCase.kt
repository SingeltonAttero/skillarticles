package ru.skillbranch.skillarticles.domain

import io.reactivex.rxjava3.core.Observable
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.models.Dish
import java.util.*

class SearchUseCase(private val repository: DishesRepositoryContract) {
    fun getDishes() = repository.getCachedDishes()
    fun findDishesByName(searchText: String): Observable<List<Dish>> = repository.getCachedDishes()
        .map { dishes ->
            dishes.filter {
                it.name.toLowerCase(Locale.ROOT).contains(searchText.trim().toLowerCase(Locale.ROOT))
            }
        }
}