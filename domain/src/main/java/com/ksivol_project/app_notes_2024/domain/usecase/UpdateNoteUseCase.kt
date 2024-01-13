package com.ksivol_project.app_notes_2024.domain.usecase

import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.repository.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note: NoteDomain){
        noteRepository.updateNote(note = note)
    }
}