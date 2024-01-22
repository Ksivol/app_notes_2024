package com.ksivol_project.app_notes_2024.app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksivol_project.app_notes_2024.app.MainApp
import com.ksivol_project.app_notes_2024.app.adapters.NoteAdapter
import com.ksivol_project.app_notes_2024.app.vm.MainViewModel
import com.ksivol_project.app_notes_2024.databinding.ActivityMainBinding
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: NoteAdapter

    private val mainViewModel: MainViewModel by viewModels { MainViewModel.MainViewModelProvider((this@MainActivity.applicationContext as MainApp).database) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fbNewNote.setOnClickListener {
            val i = Intent(this, NoteActivity::class.java).apply {
                putExtra(
                    DATA,
                    mainViewModel.date.value
                )
            }
            startActivity(i)
        }

        binding.fbCalendar.setOnClickListener {
            mainViewModel.datePickerDialog(this, adapter, this)
            mainViewModel.listObserver(adapter, this)
        }

        initRcView()

        mainViewModel.listObserver(adapter, this)


    }


    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        val listener = object : NoteAdapter.Clickable {
            override fun onDelete(note: NoteDomain) {
                mainViewModel.delete(note)
            }

            override fun onClick(note: NoteDomain) {
                val i = Intent(this@MainActivity, NoteActivity::class.java).apply {
                    putExtra(DATA, mainViewModel.date.value)
                    putExtra(NOTE, note)
                }
                startActivity(i)
            }
        }
        adapter = NoteAdapter(listener)
        rcView.adapter = adapter
    }

    companion object {

        const val DATA = "data"
        const val NOTE = "note"

    }
}