package com.ksivol_project.app_notes_2024.data.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ksivol_project.app_notes_2024.data.entities.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM Notes WHERE dateStart >= :dateStart AND dateStart < :dateFinish")
    fun getNoteByDate(dateStart: String, dateFinish: String): Flow<List<Note>>

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun deleteNote(id: Int)
    @Insert
    suspend fun saveNote(note : NoteData)

    @Update
    suspend fun updateNote(note : NoteData)
}