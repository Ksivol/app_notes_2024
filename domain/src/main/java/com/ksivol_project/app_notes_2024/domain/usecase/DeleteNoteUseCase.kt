package com.ksivol_project.app_notes_2024.domain.usecase

import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note: NoteDomain) {
        note.id?.let { noteRepository.deleteNote(id = it) }
    }
}