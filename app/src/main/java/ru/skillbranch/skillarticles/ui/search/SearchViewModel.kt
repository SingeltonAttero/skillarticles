package ru.skillbranch.skillarticles.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import ru.skillbranch.skillarticles.core.BaseViewModel
import ru.skillbranch.skillarticles.domain.SearchUseCase
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val mapper: DishesMapper
) : BaseViewModel() {
    private val defaultState = SearchState.Loading
    private val action = MutableLiveData<SearchState>()
    val state: LiveData<SearchState>
        get() = action

    fun initState() {
        useCase.getDishes()
            .doOnSubscribe { action.postValue(defaultState) }
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val newState = SearchState.Result(it)
                action.value = newState
            }, {
                action.value = SearchState.Error("")
                it.printStackTrace()
            }).track()
    }

    fun setSearchEvent(searchEvent: Observable<String>) {
        searchEvent
            .debounce(800L, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .doOnNext { action.postValue(SearchState.Loading) }
            .delay(1000L, TimeUnit.MILLISECONDS)
            .switchMap { useCase.findDishesByName(it) }
            .map { mapper.mapDtoToState(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val newState = if (it.isEmpty()) {
                    SearchState.Error("Данного товара не найдено, уточните запрос", true)
                } else {
                    SearchState.Result(it)
                }
                action.value = newState
            }, {
                action.value = SearchState.Error(it.message ?: "")
                it.printStackTrace()
            }).track()

    }

}