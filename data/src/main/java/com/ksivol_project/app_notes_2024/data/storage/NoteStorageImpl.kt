package com.ksivol_project.app_notes_2024.data.storage

import com.ksivol_project.app_notes_2024.data.database.MainDataBase
import com.ksivol_project.app_notes_2024.data.entities.NoteData

class NoteStorageImpl(database: MainDataBase) : NoteStorage {

    val dao = database.getDAO()

    override suspend fun saveNote(note: NoteData) {
        dao.saveNote(note = note)
    }

    override suspend fun deleteNote(id: Int) {
        dao.deleteNote(id = id)
    }

    override suspend fun updateNote(note: NoteData) {
        dao.updateNote(note = note)
    }

    override fun getNoteByDate(dateStart: String, dateFinish: String) =
        dao.getNoteByDate(
            dateStart = dateStart,
            dateFinish = dateFinish
        )


    override suspend fun searchNoteByName() {
        TODO("Not yet implemented")
    }

}