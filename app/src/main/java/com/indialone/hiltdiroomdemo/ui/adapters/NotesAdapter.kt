package com.indialone.hiltdiroomdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indialone.hiltdiroomdemo.databinding.ItemNoteBinding
import com.indialone.hiltdiroomdemo.room.NoteEntitiy
import javax.inject.Inject

class NotesAdapter @Inject constructor(): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val list= ArrayList<NoteEntitiy>()

    class NotesViewHolder(itemView: ItemNoteBinding) : RecyclerView.ViewHolder(itemView.root) {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val date = itemView.tvDate

        fun bind(note: NoteEntitiy) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            date.text = note.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addData(data: List<NoteEntitiy>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}