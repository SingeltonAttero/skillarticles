package ru.skillbranch.skillarticles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.skillarticles.core.BaseViewModel
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper

class MainViewModel(
    private val repository: DishesRepositoryContract,
    private val mapper: DishesMapper
) : BaseViewModel() {

    private val defaultState = MainState(emptyList())
    private val action = MutableLiveData<MainState>()
    val state: LiveData<MainState>
        get() = action

    init {
        repository.getDishes()
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val oldState = state.value ?: defaultState
                val newState = oldState.copy(productItems = it)
                action.value = newState
            }, {
                it.printStackTrace()
            })
    }
}