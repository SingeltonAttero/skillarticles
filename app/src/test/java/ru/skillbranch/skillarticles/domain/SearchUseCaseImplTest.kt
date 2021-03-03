package ru.skillbranch.skillarticles.domain

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Observable
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import ru.skillbranch.skillarticles.MockDataHolder
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import java.util.*

/*
* Сделать поиск с помощью возможностей БД
* */
class SearchUseCaseImplTest {
    private val repository = mock<DishesRepositoryContract>()
    private lateinit var useCase: SearchUseCase

    @Before
    fun setUp() {
        useCase = SearchUseCaseImpl(repository)
    }

    @Test
    fun `when user input search text when return actual dishes`() {
        whenever(repository.findDishesByName(any())).thenReturn(Observable.just(MockDataHolder.listDishes.filter {
            it.title.toLowerCase(Locale.ROOT).contains(
                "Чак".trim().toLowerCase(
                    Locale.ROOT
                )
            )
        }))
        val actualList = MockDataHolder.listDishes.filter {
            it.title.toLowerCase(Locale.ROOT).contains(
                "Чак".trim().toLowerCase(
                    Locale.ROOT
                )
            )
        }
        useCase.findDishesByName("Чак").test()
            .assertNoErrors()
            .assertResult(actualList)
    }

    @Test
    fun `when user input search text when return actual size or order dishes`() {
        whenever(repository.findDishesByName(any())).thenReturn(Observable.just(MockDataHolder.listDishes.sortedBy { it.title }
            .filter {
                it.title.toLowerCase(Locale.ROOT).contains(
                    "Мясная".trim().toLowerCase(
                        Locale.ROOT
                    )
                )
            }))
        val actualList = MockDataHolder.listDishes.sortedBy { it.title }.filter {
            it.title.toLowerCase(Locale.ROOT).contains(
                "Мясная".trim().toLowerCase(
                    Locale.ROOT
                )
            )
        }
        useCase.findDishesByName("Мясная").test()
            .assertNoErrors()
            .assertResult(actualList)

        verify(repository).findDishesByName(any())
    }


    @Test
    fun `when user input search text when return actual size or drop`() {
        whenever(repository.findDishesByName(any())).thenReturn(Observable.just(MockDataHolder.listDishes.sortedBy { it.title }
            .filter {
                it.title.toLowerCase(Locale.ROOT).contains(
                    "Чак".trim().toLowerCase(
                        Locale.ROOT
                    )
                )
            }))
        val testValue = useCase.findDishesByName("Мясная").test().values()
        Assertions.assertThat(testValue.size).isEqualTo(1)
        verify(repository).findDishesByName(any())
    }
}