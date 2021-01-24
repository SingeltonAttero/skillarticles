package ru.skillbranch.skillarticles.domain.entity

data class DishEntity(
    val id: String,
    val categoryId: String,
    val image: String,
    val price: String,
    val title: String
)