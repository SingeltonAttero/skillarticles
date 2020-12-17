package ru.skillbranch.skillarticles.ui.main

import ru.skillbranch.skillarticles.core.adapter.ProductItemState

sealed class MainState {
    object Loader : MainState()
    data class Error(val message: String, val error: Throwable) : MainState()
    data class Result(val productItems: List<ProductItemState>) : MainState()
}