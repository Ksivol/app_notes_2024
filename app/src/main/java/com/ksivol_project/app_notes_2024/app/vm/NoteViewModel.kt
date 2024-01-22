package com.ksivol_project.app_notes_2024.app.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ksivol_project.app_notes_2024.data.database.MainDataBase
import com.ksivol_project.app_notes_2024.data.repository.NoteRepositoryImpl
import com.ksivol_project.app_notes_2024.data.storage.NoteStorageImpl
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.usecase.SaveNoteUseCase
import com.ksivol_project.app_notes_2024.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.launch

class NoteViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    fun save(note: NoteDomain) = viewModelScope.launch {
        saveNoteUseCase.execute(note)
    }

    fun update(note: NoteDomain) = viewModelScope.launch {
        updateNoteUseCase.execute(note)
    }

    class NoteViewModelProvider(database: MainDataBase) : ViewModelProvider.Factory {
        private val saveNoteUseCase = SaveNoteUseCase(NoteRepositoryImpl(NoteStorageImpl(database)))


        private val updateNoteUseCase =
            UpdateNoteUseCase(NoteRepositoryImpl(NoteStorageImpl(database)))

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(saveNoteUseCase, updateNoteUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }

    }
}