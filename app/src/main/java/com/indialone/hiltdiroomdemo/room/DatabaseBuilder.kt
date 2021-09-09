package com.indialone.hiltdiroomdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [NoteEntitiy::class], version = 1)
abstract class DatabaseBuilder : RoomDatabase() {

    abstract fun noteDao(): NotesDao

}