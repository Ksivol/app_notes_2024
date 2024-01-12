package com.ksivol_project.app_notes_2024.data.storage

interface NoteStorage {

    suspend fun deleteNote()

    suspend fun saveNote()

    suspend fun updateNote()

    suspend fun getNoteByDate()

    suspend fun searchNoteByName()
}