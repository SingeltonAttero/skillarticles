package ru.skillbranch.skillarticles.repository.error

class EmptyDishesError(val messageDishes: String = "") : Throwable(messageDishes) {
}