package com.ksivol_project.app_notes_2024.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ksivol_project.app_notes_2024.app.MainApp
import com.ksivol_project.app_notes_2024.app.utils.getSerializable
import com.ksivol_project.app_notes_2024.app.vm.NoteViewModel
import com.ksivol_project.app_notes_2024.databinding.ActivityNoteBinding
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding


    private val noteViewModel: NoteViewModel by viewModels { NoteViewModel.NoteViewModelProvider((this@NoteActivity.applicationContext as MainApp).database) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSaveButtonClick()
        fillNote()
    }


    private fun fillNote() = with(binding) {
        val formatter = DateTimeFormatter.ofPattern("HH")

        if (intent.hasExtra(MainActivity.NOTE)) {
            val note: NoteDomain? =
                intent.getSerializable(MainActivity.NOTE, NoteDomain::class.java)
            edTitle.setText(note?.name)
            edTime.setText(
                (note?.dateStart?.toInstant()?.atZone(ZoneId.of("UTC"))?.toLocalTime()
                    ?.format(formatter)!!.toString())
            )
            edDescription.setText(note.description)
        }
    }
    
    fun onSaveButtonClick() {
        binding.fbSave.setOnClickListener {
            val localTime = LocalTime.parse(
                binding.edTime.text.toString(),
                DateTimeFormatter.ofPattern("HH")
            )
            val plusHour = localTime.plusHours(1)

            if (intent.hasExtra(MainActivity.NOTE)) {
                noteViewModel.update(
                    intent.getSerializable(MainActivity.NOTE, NoteDomain::class.java)?.copy(
                        dateStart = Timestamp(
                            LocalDateTime.of(
                                intent.getSerializable(
                                    name = MainActivity.DATA,
                                    Calendar::class.java
                                )
                                    ?.getTime()?.toInstant()
                                    ?.atZone(ZoneId.systemDefault())
                                    ?.toLocalDate()!!,
                                localTime
                            ).toInstant(ZoneOffset.UTC).toEpochMilli()
                        ),
                        dateFinish = Timestamp(
                            LocalDateTime.of(
                                intent.getSerializable(
                                    name = MainActivity.DATA,
                                    Calendar::class.java
                                )
                                    ?.getTime()?.toInstant()
                                    ?.atZone(ZoneId.systemDefault())
                                    ?.toLocalDate()!!,
                                plusHour
                            ).toInstant(ZoneOffset.UTC).toEpochMilli()
                        ),
                        name = binding.edTitle.text.toString(),
                        description = binding.edDescription.text.toString()
                    )!!
                )

            } else {
                noteViewModel.save(
                    NoteDomain(
                        id = null,
                        dateStart = Timestamp(
                            LocalDateTime.of(
                                intent.getSerializable(
                                    name = MainActivity.DATA,
                                    Calendar::class.java
                                )
                                    ?.getTime()?.toInstant()
                                    ?.atZone(ZoneId.systemDefault())
                                    ?.toLocalDate()!!,
                                localTime
                            ).toInstant(ZoneOffset.UTC).toEpochMilli()
                        ),
                        dateFinish = Timestamp(
                            LocalDateTime.of(
                                intent.getSerializable(
                                    name = MainActivity.DATA,
                                    Calendar::class.java
                                )
                                    ?.getTime()?.toInstant()
                                    ?.atZone(ZoneId.systemDefault())
                                    ?.toLocalDate()!!,
                                plusHour
                            ).toInstant(ZoneOffset.UTC).toEpochMilli()
                        ),
                        name = binding.edTitle.text.toString(),
                        description = binding.edDescription.text.toString()
                    )
                )
            }
            finish()
        }
    }


}