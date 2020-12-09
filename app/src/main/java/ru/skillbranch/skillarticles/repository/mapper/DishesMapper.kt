package ru.skillbranch.skillarticles.repository.mapper

import ru.skillbranch.skillarticles.core.adapter.ProductItemState
import ru.skillbranch.skillarticles.repository.models.Dish

class DishesMapper {
    fun mapDtoToState(dishesDto: List<Dish>): List<ProductItemState> =
        dishesDto.map { ProductItemState(it.image, "${it.price} ₽", it.name) }
}