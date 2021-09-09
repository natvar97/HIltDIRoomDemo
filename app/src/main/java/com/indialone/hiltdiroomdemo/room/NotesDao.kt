package com.indialone.hiltdiroomdemo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntitiy)

    @Query("SELECT * FROM notes_table ORDER BY date DESC")
    suspend fun getNotes(): List<NoteEntitiy>

}