package com.ksivol_project.app_notes_2024.app.vm

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ksivol_project.app_notes_2024.app.adapters.NoteAdapter
import com.ksivol_project.app_notes_2024.data.database.MainDataBase
import com.ksivol_project.app_notes_2024.data.repository.NoteRepositoryImpl
import com.ksivol_project.app_notes_2024.data.storage.NoteStorageImpl
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import com.ksivol_project.app_notes_2024.domain.usecase.DeleteNoteUseCase
import com.ksivol_project.app_notes_2024.domain.usecase.GetNoteByDateUseCase
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone

class MainViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNoteByDateUseCase: GetNoteByDateUseCase
) : ViewModel() {

    private val _date = MutableLiveData(Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    })
    val date: LiveData<Calendar> = _date
    private fun getListByDate(time: Long) =
        getNoteByDateUseCase.execute(time).asLiveData()

    fun delete(note: NoteDomain) = viewModelScope.launch {
        deleteNoteUseCase.execute(note)
    }

//    fun getByDate(date: Long) : LiveData<List<NoteDomain>> {
//        return getNoteByDateUseCase.execute(date).asLiveData()
//    }

    class MainViewModelProvider(database: MainDataBase) : ViewModelProvider.Factory {

        private val deleteNoteUseCase =
            DeleteNoteUseCase(NoteRepositoryImpl(NoteStorageImpl(database)))

        private val getNoteByDateUseCase =
            GetNoteByDateUseCase(NoteRepositoryImpl(NoteStorageImpl(database)))


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(deleteNoteUseCase, getNoteByDateUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }

    fun listObserver(adapter: NoteAdapter, lifecycleOwner: LifecycleOwner) {
        date.observe(lifecycleOwner) { date ->
            getListByDate(date.timeInMillis).observe(lifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }
    }

    fun datePickerDialog(context: Context, adapter: NoteAdapter, lifecycleOwner: LifecycleOwner) {
        DatePickerDialog(
            context,
            listenerDate(adapter, lifecycleOwner),
            _date.value!![Calendar.YEAR],
            _date.value!![Calendar.MONTH],
            _date.value!![Calendar.DAY_OF_MONTH],
        ).show()
    }

    private fun listenerDate(adapter: NoteAdapter, lifecycleOwner: LifecycleOwner): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            _date.value!!.apply {
                set(year, month, dayOfMonth)
            }
            listObserver(adapter, lifecycleOwner)
            Log.d("DataObserver", "${_date.value?.timeInMillis}")
        }
    }




}