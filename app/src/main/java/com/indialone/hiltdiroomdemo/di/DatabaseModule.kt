package com.indialone.hiltdiroomdemo.di

import android.content.Context
import androidx.room.Room
import com.indialone.hiltdiroomdemo.room.DatabaseBuilder
import com.indialone.hiltdiroomdemo.room.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNoteDao(database: DatabaseBuilder): NotesDao {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseBuilder {
        return Room.databaseBuilder(
            context,
            DatabaseBuilder::class.java,
            "notes_database"
        ).build()
    }

}