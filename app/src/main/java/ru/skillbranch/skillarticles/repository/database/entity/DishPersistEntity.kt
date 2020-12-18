package ru.skillbranch.skillarticles.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes_table")
data class DishPersistEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "oldPrice") val oldPrice: Int,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "likes") val likes: Long,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "commentsCount") val commentsCount: Int,
    @ColumnInfo(name = "active") val active: Boolean,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String
)