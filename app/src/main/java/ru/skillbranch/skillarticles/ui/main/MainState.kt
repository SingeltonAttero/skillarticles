package ru.skillbranch.skillarticles.ui.main

import ru.skillbranch.skillarticles.core.adapter.CategoryItemState
import ru.skillbranch.skillarticles.core.adapter.ProductItemState

sealed class MainState {
    object Loader : MainState()
    data class Error(
        val message: String, val error: Throwable,
        val isRefresh: Boolean = true,
        val categories: List<CategoryItemState> = emptyList()
    ) : MainState()

    data class Result(
        val productItems: List<ProductItemState>,
        val categories: List<CategoryItemState>
    ) : MainState()
}