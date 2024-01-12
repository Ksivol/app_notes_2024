package com.ksivol_project.app_notes_2024.domain.repository

interface NoteRepository {

    suspend fun saveNote()

    suspend fun deleteNote()

    suspend fun updateNote()

    suspend fun getNoteByDate()

    suspend fun SearchNoteByName()


}