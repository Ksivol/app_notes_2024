package com.ksivol_project.app_notes_2024.data.repository

import com.ksivol_project.app_notes_2024.data.storage.NoteStorage
import com.ksivol_project.app_notes_2024.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteStorage: NoteStorage) : NoteRepository {

    override suspend fun deleteNote() {
        noteStorage.deleteNote()
    }

    override suspend fun saveNote() {
        noteStorage.saveNote()
    }

    override suspend fun updateNote() {
        noteStorage.updateNote()
    }

    override suspend fun getNoteByDate() {
        noteStorage.getNoteByDate()
    }

    override suspend fun searchNoteByName() {
        TODO("Not yet implemented")
    }

}