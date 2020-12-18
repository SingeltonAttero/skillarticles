package ru.skillbranch.skillarticles.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.skillbranch.skillarticles.repository.database.dao.DishesDao
import ru.skillbranch.skillarticles.repository.database.entity.DishPersistEntity

@Database(entities = [DishPersistEntity::class], version = 1, exportSchema = false)
abstract class SkillArticlesRoomDatabase : RoomDatabase() {
    abstract fun dishesDao(): DishesDao
}

object DatabaseProvider {
    fun newInstance(context: Context): SkillArticlesRoomDatabase =
        Room.databaseBuilder(context.applicationContext, SkillArticlesRoomDatabase::class.java, "sa_test_database")
            .build()
}