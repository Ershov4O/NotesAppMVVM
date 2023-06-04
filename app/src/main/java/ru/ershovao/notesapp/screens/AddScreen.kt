package ru.ershovao.notesapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.MainViewModel
import ru.ershovao.notesapp.MainViewModelFactory
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.ui.theme.NotesAppTheme

@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember {
        mutableStateOf("")
    }
    var subTitle by remember {
        mutableStateOf("")
    }
    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add new note",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = subTitle.isNotEmpty() && title.isNotEmpty()
                },
                isError = title.isEmpty(),
                label = { Text(text = "Note title") }
            )
            OutlinedTextField(
                value = subTitle,
                onValueChange = {
                    subTitle = it
                    isButtonEnabled = subTitle.isNotEmpty() && title.isNotEmpty()
                },
                isError = subTitle.isEmpty(),
                label = { Text(text = "Note subtitle") }
            )

            Button(modifier = Modifier
                .width(200.dp)
                .padding(vertical = 32.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subTitle = subTitle)) {
                        navController.popBackStack()
                    }
                }) {
                Text(text = "Save")
            }
        }

    }
}

@Preview
@Composable
fun AddPreview() {
    NotesAppTheme {
        val context = LocalContext.current
        val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}