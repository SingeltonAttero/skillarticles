package ru.skillbranch.skillarticles.repository.mapper

import ru.skillbranch.skillarticles.core.adapter.ProductItemState
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import ru.skillbranch.skillarticles.repository.database.entity.DishPersistEntity
import ru.skillbranch.skillarticles.repository.models.Dish

class DishesMapper {
    fun mapDtoToState(dishEntity: List<DishEntity>): List<ProductItemState> =
        dishEntity.map { ProductItemState(it.image, it.price, it.title) }

    fun mapDtoToEntity(dishesDto: List<Dish>): List<DishEntity> =
        dishesDto.map { DishEntity(it.id ?: "", it.image ?: "", "${it.price} ₽", it.name ?: "") }

    fun mapDtoToPersist(dishesDto: List<Dish>): List<DishPersistEntity> =
        dishesDto.map {
            DishPersistEntity(
                it.id ?: "",
                it.name ?: "",
                it.description ?: "",
                it.image ?: "",
                it.oldPrice ?: 0,
                it.price ?: 0L,
                it.rating ?: 0F,
                it.likes ?: 0,
                it.category ?: "",
                it.commentsCount ?: 0,
                it.active ?: false,
                it.createdAt ?: "",
                it.updatedAt ?: ""
            )
        }

    fun mapPersistToEntity(dishesPersist: List<DishPersistEntity>): List<DishEntity> =
        dishesPersist.map { DishEntity(it.id, it.image, "${it.price} ₽", it.name) }
}