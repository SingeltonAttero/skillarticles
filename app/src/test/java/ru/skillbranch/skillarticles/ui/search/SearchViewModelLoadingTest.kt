package ru.skillbranch.skillarticles.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper


/**
 * Состояния загрузки при поиске товаров
 **/
@RunWith(JUnit4::class)
class SearchViewModelLoadingTest {

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
    fun `when loading and init should state Loading`() {
        val hotObserve: ReplaySubject<List<DishEntity>> = ReplaySubject.create()
        whenever(useCase.getDishes()).thenReturn(hotObserve.hide().single(listOf()))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Loading)

        verify(useCase).getDishes()
    }

    @Test
    fun `when delay loading and init should state Loading`() {
        val hotObserve: ReplaySubject<List<DishEntity>> = ReplaySubject.create()
        whenever(useCase.getDishes()).thenReturn(hotObserve.hide().single(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Loading)

        verify(useCase).getDishes()
    }

    @Test
    fun `when on error and init should not state loading`() {
        whenever(useCase.getDishes()).thenReturn(Single.error(RuntimeException()))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isNotEqualTo(SearchState.Loading)

        verify(useCase).getDishes()
    }


    @Test
    fun `when on full result data and init should not state loading`() {
        whenever(useCase.getDishes()).thenReturn(Single.just(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isNotEqualTo(SearchState.Loading)

        verify(useCase).getDishes()
    }


}