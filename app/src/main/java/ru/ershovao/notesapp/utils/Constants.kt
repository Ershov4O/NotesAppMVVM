package ru.ershovao.notesapp.utils

import ru.ershovao.notesapp.data.DatabaseRepository

lateinit var DB_TYPE: String
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String

object Constants {
    object Keys {
        const val ID = "note_id"
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val FIREBASE_ID = "firebaseId"
        const val TITLE = "title"
        const val SUBTITLE = "subTitle"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}
