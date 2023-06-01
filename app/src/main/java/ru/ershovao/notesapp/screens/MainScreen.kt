package ru.ershovao.notesapp.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.MainViewModel
import ru.ershovao.notesapp.MainViewModelFactory
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    val notes = viewModel.readTest.observeAsState(listOf()).value

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(route = NavRoutes.Add.route) }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
        }
    }) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues)) {
//            NoteItem("Title", "Subtitle", navController)
//            NoteItem("Title 2", "Subtitle 2", navController)
//            NoteItem("Title 3", "Subtitle 3", navController)
//            NoteItem("Title 4", "Subtitle 4", navController)
//        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(notes) { note ->
                NoteItem(note = note, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable { navController.navigate(route = NavRoutes.Note.route) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.subTitle
            )
        }
    }
}

@Preview
@Composable
fun PrevMain() {
    NotesAppTheme {
        MainScreen(navController = rememberNavController())
    }
}