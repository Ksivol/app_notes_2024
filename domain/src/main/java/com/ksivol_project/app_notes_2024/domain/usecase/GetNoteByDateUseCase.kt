package com.ksivol_project.app_notes_2024.domain.usecase

import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow


class GetNoteByDateUseCase(private val noteRepository: NoteRepository) {

    fun execute(note: NoteDomain): Flow<List<NoteDomain>> {
        return noteRepository.getNoteByDate(
            dateStart = note.dateStart.toString(),
            dateFinish = note.dateFinish.toString(),
        )
    }
}