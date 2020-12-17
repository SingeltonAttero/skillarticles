package ru.skillbranch.skillarticles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.skillarticles.core.BaseViewModel
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.error.EmptyDishesError
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper

class MainViewModel(
    private val repository: DishesRepositoryContract,
    private val mapper: DishesMapper
) : BaseViewModel() {

    private val defaultState = MainState.Loader
    private val action = MutableLiveData<MainState>()
    val state: LiveData<MainState>
        get() = action

    init {
        loadDishes()
    }

    fun loadDishes() {
        repository.getDishes()
            .doOnSubscribe { action.value = defaultState }
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val newState = MainState.Result(it)
                action.value = newState
            }, {
                if (it is EmptyDishesError) {
                    action.value = MainState.Error(it.messageDishes, it)
                } else {
                    action.value = MainState.Error("Что то пошло не по плану", it)
                }
                it.printStackTrace()
            }).track()
    }
}