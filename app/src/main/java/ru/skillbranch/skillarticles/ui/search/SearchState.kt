package ru.skillbranch.skillarticles.ui.search

import ru.skillbranch.skillarticles.core.adapter.ProductItemState

sealed class SearchState {
    data class Result(val items: List<ProductItemState>) : SearchState()
    object Loading : SearchState()
    data class Error(val errorDescription: String, val emptyLIst: Boolean = false) : SearchState()
}