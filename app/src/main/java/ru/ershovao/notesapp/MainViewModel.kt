package ru.ershovao.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value = when (dbType.value) {
            TYPE_ROOM -> {
                listOf(
                    Note(title = "title 1", subTitle = "subtitle 1"),
                    Note(title = "title 2", subTitle = "subtitle 2"),
                    Note(title = "title 3", subTitle = "subtitle 3"),
                    Note(title = "title 4", subTitle = "subtitle 4"),
                    Note(title = "title 5", subTitle = "subtitle 5")
                )
            }

            else -> {
                listOf()
            }
        }
    }

    fun initDB(type: String) {
        dbType.value = type
        Log.d("dataBaseInit", "MainViewModel initDB with $type")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}