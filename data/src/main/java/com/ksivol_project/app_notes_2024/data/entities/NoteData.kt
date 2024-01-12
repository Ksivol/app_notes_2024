package com.ksivol_project.app_notes_2024.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "Notes")
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "dateStart")
    val dateStart: Timestamp,

    @ColumnInfo(name = "dateFinish")
    val dateFinish: Timestamp,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    )
