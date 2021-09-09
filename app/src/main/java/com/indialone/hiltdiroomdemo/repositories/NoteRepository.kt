package com.indialone.hiltdiroomdemo.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.indialone.hiltdiroomdemo.di.DatabaseModule
import com.indialone.hiltdiroomdemo.room.DatabaseBuilder
import com.indialone.hiltdiroomdemo.room.NoteEntitiy
import com.indialone.hiltdiroomdemo.room.NotesDao
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NotesDao) {

    @WorkerThread
    suspend fun insert(note: NoteEntitiy) = noteDao.insert(note)

    @WorkerThread
    suspend fun getNotes() = noteDao.getNotes()


}