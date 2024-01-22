package com.ksivol_project.app_notes_2024.app.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ksivol_project.app_notes_2024.R
import com.ksivol_project.app_notes_2024.databinding.RcNoteItemBinding
import com.ksivol_project.app_notes_2024.domain.entities.NoteDomain
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class NoteAdapter(private val listener: Clickable) :
    ListAdapter<NoteDomain, NoteAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = RcNoteItemBinding.bind(itemView)


        fun setData(note: NoteDomain, listener: Clickable) = with(binding) {
            tvTime.text = StringBuilder(timeToString(note.dateStart.time))
                .append(" - ")
                .append(timeToString(note.dateFinish.time))
            tvTitle.text = note.name
            imDelete.setOnClickListener {
                listener.onDelete(note)
            }
            noteLayout.setOnClickListener{
                listener.onClick(note)
            }
            Log.d(
                "DateForm",
                "Date start: ${timeToString(note.dateStart.time)} \nDate Finish: ${timeToString(note.dateFinish.time)}"
            )
        }

        @SuppressLint("SimpleDateFormat")
        private fun timeToString(millis: Long): String {
            val formatter = SimpleDateFormat("HH").apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            return formatter.format(Date(millis))
        }

    }

    class ItemComparator : DiffUtil.ItemCallback<NoteDomain>() {
        override fun areItemsTheSame(oldItem: NoteDomain, newItem: NoteDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteDomain, newItem: NoteDomain): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(inflater.inflate(R.layout.rc_note_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    interface Clickable {
        fun onDelete(note: NoteDomain)
        fun onClick(note: NoteDomain)

    }


}