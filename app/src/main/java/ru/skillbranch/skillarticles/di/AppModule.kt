package ru.skillbranch.skillarticles.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.skillbranch.skillarticles.core.ResourceManager
import ru.skillbranch.skillarticles.domain.SearchUseCase
import ru.skillbranch.skillarticles.repository.DishesRepository
import ru.skillbranch.skillarticles.repository.DishesRepositoryContract
import ru.skillbranch.skillarticles.repository.database.DatabaseProvider
import ru.skillbranch.skillarticles.repository.database.SkillArticlesRoomDatabase
import ru.skillbranch.skillarticles.repository.http.client.DeliveryRetrofitProvider
import ru.skillbranch.skillarticles.repository.mapper.DishesMapper
import ru.skillbranch.skillarticles.ui.main.MainViewModel
import ru.skillbranch.skillarticles.ui.search.SearchViewModel

object AppModule {
    fun appModule() = module {
        single { DeliveryRetrofitProvider.createRetrofit() }
        single<DishesRepositoryContract> { DishesRepository(api = get(), mapper = get(), dishesDao = get()) }
        single { ResourceManager(context = get()) }
        single { SearchUseCase(get()) }
        single { DishesMapper() }
    }

    fun databaseModule() = module {
        single { DatabaseProvider.newInstance(context = get()) }
        single { get<SkillArticlesRoomDatabase>().dishesDao() }
    }

    fun viewModelModule() = module {
        viewModel { MainViewModel(repository = get(), mapper = get()) }
        viewModel { SearchViewModel(useCase = get(), mapper = get()) }
    }
}