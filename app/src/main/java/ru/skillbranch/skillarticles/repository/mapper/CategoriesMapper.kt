package ru.skillbranch.skillarticles.repository.mapper

import ru.skillbranch.skillarticles.core.adapter.CategoryItemState
import ru.skillbranch.skillarticles.repository.models.Category

class CategoriesMapper {

    fun mapDtoToState(dto: List<Category>): List<CategoryItemState> =
        dto.map { CategoryItemState(it.categoryId, it.name) }

}