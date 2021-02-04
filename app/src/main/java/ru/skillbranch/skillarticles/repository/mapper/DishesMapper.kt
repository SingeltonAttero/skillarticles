package ru.skillbranch.skillarticles.repository.mapper

import ru.skillbranch.skillarticles.core.adapter.ProductItemState
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.database.entity.DishPersistEntity
import ru.skillbranch.skillarticles.repository.models.Dish

interface DishesMapper {
    fun mapDtoToState(dishEntity: List<DishEntity>): List<ProductItemState>
    fun mapDtoToEntity(dishesDto: List<Dish>): List<DishEntity>
    fun mapDtoToPersist(dishesDto: List<Dish>): List<DishPersistEntity>
    fun mapPersistToEntity(dishesPersist: List<DishPersistEntity>): List<DishEntity>
}