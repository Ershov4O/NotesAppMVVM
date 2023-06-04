package ru.ershovao.notesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.ershovao.notesapp.data.room.dao.NoteRoomDao
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.utils.Constants.Keys.NOTES_DATABASE

@Database(entities = [Note::class], version = 2)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getRoomDao(): NoteRoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppRoomDatabase::class.java, NOTES_DATABASE)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE as AppRoomDatabase
            } else {
                INSTANCE as AppRoomDatabase
            }

        }
    }
}