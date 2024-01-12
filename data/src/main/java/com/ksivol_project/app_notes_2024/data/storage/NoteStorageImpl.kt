package com.ksivol_project.app_notes_2024.data.storage

import com.ksivol_project.app_notes_2024.data.database.MainDataBase

class NoteStorageImpl(database: MainDataBase) : NoteStorage {

    val dao = database.getDAO()

    override suspend fun saveNote() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote() {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote() {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteByDate() {
        TODO("Not yet implemented")
    }

    override suspend fun searchNoteByName() {
        TODO("Not yet implemented")
    }

}