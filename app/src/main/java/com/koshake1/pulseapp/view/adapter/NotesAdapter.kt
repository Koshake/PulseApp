package com.koshake1.pulseapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.pulseapp.R
import com.koshake1.pulseapp.databinding.ItemPulseBinding
import com.koshake1.pulseapp.model.data.Note

val DIFF_UTIL: DiffUtil.ItemCallback<Note> = object : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return true
    }
}

class NotesAdapter(val noteHandler: (Note) -> Unit) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(
        parent: ViewGroup,
        private val binding: ItemPulseBinding = ItemPulseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        private lateinit var currentNote: Note

        private val clickListener: View.OnClickListener = View.OnClickListener {
            noteHandler(currentNote)
        }

        fun bind(item: Note) {
            currentNote = item
            with(binding) {
                textviewPressure.text = itemView.context.resources.getString(R.string.pressure, item.sys_pressure, item.dias_pressure)
                textviewPulse.text = item.pulse
                textviewTime.text = item.time
                root.setOnClickListener(clickListener)
            }
        }
    }
}