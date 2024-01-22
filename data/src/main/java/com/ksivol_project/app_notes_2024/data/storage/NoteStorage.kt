package com.ksivol_project.app_notes_2024.data.storage

import com.ksivol_project.app_notes_2024.data.entities.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteStorage {

    suspend fun saveNote(note: NoteData)

    suspend fun deleteNote(id: Int)

    suspend fun updateNote(note: NoteData)

    fun getNoteByDate(dateStart: Long, dateFinish: Long): Flow<List<NoteData>>

    suspend fun searchNoteByName()

}