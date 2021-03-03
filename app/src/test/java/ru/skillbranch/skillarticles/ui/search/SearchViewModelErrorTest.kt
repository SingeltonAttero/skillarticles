package ru.skillbranch.skillarticles.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.ReplaySubject
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.skillbranch.skillarticles.MockDataHolder
import ru.skillbranch.skillarticles.RxImmediateSchedulerRule
import ru.skillbranch.skillarticles.domain.SearchUseCase
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.error.EmptyDishesError
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper


/*
* Обработка ошибок при поиске товаров
* */
@RunWith(JUnit4::class)
class SearchViewModelErrorTest {

    @Rule
    @JvmField
    var executorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val mapper: DishesMapper = mock()
    private val useCase: SearchUseCase = mock()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(useCase, mapper)
    }

    @Test
    fun `when use case error data should value return in SearchState Error`() {
        whenever(useCase.getDishes()).thenReturn(Single.error(EmptyDishesError("")))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Error(""))

        verify(useCase).getDishes()
    }

    @Test
    fun `when search in dishes should return error use case show Error state`() {
        whenever(useCase.findDishesByName(any())).thenReturn(Observable.error(EmptyDishesError("")))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.setSearchEvent(Observable.just("Test"))
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Error(""))
        verify(useCase).findDishesByName(any())
    }

    @Test
    fun `when use loadingState data should value return in SearchState Error`() {
        val hotObserve: ReplaySubject<List<DishEntity>> = ReplaySubject.create()
        whenever(useCase.getDishes()).thenReturn(hotObserve.hide().single(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isNotEqualTo(SearchState.Error(""))

        verify(useCase).getDishes()
    }

    @Test
    fun `when search in dishes should return loading not show Error state`() {
        whenever(useCase.getDishes()).thenReturn(Single.just(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.setSearchEvent(Observable.just("Test"))
        Assertions.assertThat(viewModel.state.value).isNotEqualTo(SearchState.Error(""))
        verify(useCase).findDishesByName(any())
    }

}