package com.ksivol_project.app_notes_2024.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ksivol_project.app_notes_2024.data.entities.NoteData


@Database(entities = [NoteData::class], version = 1)

abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "notes.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}