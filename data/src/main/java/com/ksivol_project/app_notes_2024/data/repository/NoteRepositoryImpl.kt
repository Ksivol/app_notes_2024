package com.ksivol_project.app_notes_2024.data.repository

import com.ksivol_project.app_notes_2024.data.entities.NoteData
import com.ksivol_project.app_notes_2024.data.storage.NoteStorage
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteStorage: NoteStorage) : NoteRepository {

    override suspend fun saveNote(note: NoteDomain) {
        noteStorage.saveNote(toData(note))
    }

    override suspend fun deleteNote(id: Int) {
        noteStorage.deleteNote(id = id)
    }

    override suspend fun updateNote(note: NoteDomain) {
        noteStorage.updateNote(toData(note))
    }

    override fun getNoteByDate(dateStart: String, dateFinish: String): Flow<List<NoteDomain>> =
        noteStorage.getNoteByDate(dateStart = dateStart, dateFinish = dateFinish)
            .map { list ->
                list.map { noteData ->
                    toDomain(noteData)
                }
            }

    private fun toDomain(note: NoteData): NoteDomain {
        return NoteDomain(
            id = note.id,
            dateStart = note.dateStart,
            dateFinish = note.dateFinish,
            name = note.name,
            description = note.description
        )
    }

    private fun toData(note: NoteDomain): NoteData {
        return NoteData(
            id = note.id,
            dateStart = note.dateStart,
            dateFinish = note.dateFinish,
            name = note.name,
            description = note.description
        )
    }

    override suspend fun searchNoteByName() {
        TODO("Not yet implemented")
    }
}