package ru.skillbranch.skillarticles

import ru.skillbranch.skillarticles.core.adapter.ProductItemState
import ru.skillbranch.skillarticles.domain.entity.DishEntity
import java.util.*

object MockDataHolder {
    val searchStateList = listOf(
        ProductItemState(
            id = UUID.randomUUID().toString(),
            image = "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            price = "189",
            title = "Бургер \"Франция\"",
            categoryId = "5ed8da011f071c00465b1fdc"
        ),
        ProductItemState(
            id = UUID.randomUUID().toString(),
            image = "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            price = "189",
            title = "Бургер \"Франция\"Бургер \\\"Чак Норрис\\\"",
            categoryId = "5ed8da011f071c00465b1fdc"
        ),
        ProductItemState(
            id = UUID.randomUUID().toString(),
            image = "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            price = "189",
            title = "Бургер \\\"Чак Норрис\\\" \"Франция\"",
            categoryId = "5ed8da011f071c00465b1fdc"
        ),
        ProductItemState(
            id = UUID.randomUUID().toString(),
            image = "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            price = "189",
            title = "Бургер \"Бургер \\\"Чак Норрис\\\"\"",
            categoryId = "5ed8da011f071c00465b1fdc"
        ),
        ProductItemState(
            id = UUID.randomUUID().toString(),
            image = "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            price = "189",
            title = "Бургер \\\"Чак Норрис\\\"",
            categoryId = "5ed8da011f071c00465b1fdc"
        ),
    )


    val listDishes = listOf(
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fdc",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "189",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe0",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \\\"Чак Норрис\\\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe2",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe6",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "189",
            "\"Бургер \\\"Франция\\\"\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe4",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Пицца Мясная с двойной начинкой"
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fee",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe0",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \\\"Чак Норрис\\\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe2",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe6",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "189",
            "\"Бургер \\\"Франция\\\"\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe4",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Пицца Мясная с двойной начинкой"
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fee",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe0",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \\\"Чак Норрис\\\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe2",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Бургер \"Франция\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe6",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "189",
            "\"Бургер \\\"Франция\\\"\""
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fe4",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "279",
            "Пицца Мясная с двойной начинкой"
        ),
        DishEntity(
            UUID.randomUUID().toString(),
            "5ed8da011f071c00465b1fee",
            "https://skill-branch.ru/img/sb-delivery/Suwi_i_Rolly.svg",
            "289",
            "Бургер \"Франция\""
        ),
    )
}