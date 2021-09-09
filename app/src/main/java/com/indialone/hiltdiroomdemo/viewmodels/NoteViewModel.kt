package com.indialone.hiltdiroomdemo.viewmodels

import androidx.lifecycle.*
import com.indialone.hiltdiroomdemo.repositories.NoteRepository
import com.indialone.hiltdiroomdemo.room.NoteEntitiy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

//@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {


    private var notes = MutableLiveData<List<NoteEntitiy>>()

    fun fetchNotes() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val notesList = async {
                        noteRepository.getNotes()
                    }
                    notes.postValue(notesList.await())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertNote(noteEntitiy: NoteEntitiy) {
        viewModelScope.launch {
            try {
                coroutineScope {
                    noteRepository.insert(noteEntitiy)
                }

            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    fun getNotes(): LiveData<List<NoteEntitiy>> = notes

}