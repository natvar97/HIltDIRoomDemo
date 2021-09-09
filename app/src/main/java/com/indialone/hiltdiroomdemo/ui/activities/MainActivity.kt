package com.indialone.hiltdiroomdemo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.indialone.hiltdiroomdemo.viewmodels.NoteViewModel
import com.indialone.hiltdiroomdemo.ui.adapters.NotesAdapter
import com.indialone.hiltdiroomdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var notesAdapter: NotesAdapter

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getNotes()

        mBinding.fabAddNote.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
        }

    }

    fun getNotes() {
        noteViewModel.fetchNotes()
        noteViewModel.getNotes().observe(this) {
            notesAdapter.addData(it)
            mBinding.rvNotes.layoutManager = LinearLayoutManager(this)
            mBinding.rvNotes.adapter = notesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        getNotes()
    }

}