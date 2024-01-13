package com.ksivol_project.app_notes_2024.domain.repository

import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun saveNote(note: NoteDomain)

    suspend fun deleteNote(id: Int)

    suspend fun updateNote(note: NoteDomain)

    fun getNoteByDate(dateStart: String, dateFinish: String): Flow<List<NoteDomain>>

    suspend fun searchNoteByName()


}