package ru.skillbranch.skillarticles.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.skillbranch.skillarticles.core.BaseViewModel
import ru.skillbranch.skillarticles.domain.SearchUseCase
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val mapper: DishesMapper
) : BaseViewModel() {
    private val defaultState = SearchState(emptyList())
    private val action = MutableLiveData<SearchState>()
    val state: LiveData<SearchState>
        get() = action

    init {
        useCase.getDishes()
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val oldState = action.value ?: defaultState
                val newState = oldState.copy(items = it)
                action.value = newState
            }, {
                it.printStackTrace()
            }).track()
    }

    fun setSearchEvent(searchEvent: Observable<String>) {
        searchEvent
            .debounce(800L, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { useCase.findDishesByName(it) }
            .map { mapper.mapDtoToState(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val oldState = action.value ?: defaultState
                val newState = oldState.copy(items = it)
                action.value = newState
            }, {
                it.printStackTrace()
            }).track()

    }

}