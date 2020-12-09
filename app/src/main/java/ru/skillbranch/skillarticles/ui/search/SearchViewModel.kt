package ru.skillbranch.skillarticles.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.skillarticles.core.BaseViewModel
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper

class SearchViewModel(
    private val repository: DishesRepositoryContract,
    private val mapper: DishesMapper
) : BaseViewModel() {

    private val defaultState = SearchState(emptyList())
    private val action = MutableLiveData<SearchState>()
    val state: LiveData<SearchState>
        get() = action

    init {
        repository.getCachedDishes()
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val oldState = action.value ?: defaultState
                val newState = oldState.copy(items = it)
                action.value = newState
            }, {
                it.printStackTrace()
            })
    }

}