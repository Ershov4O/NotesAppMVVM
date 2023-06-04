package ru.ershovao.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ershovao.notesapp.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val subTitle: String = "",
    val firebaseId: String = ""
)
