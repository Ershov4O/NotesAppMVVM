package ru.ershovao.notesapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.MainViewModel
import ru.ershovao.notesapp.MainViewModelFactory
import ru.ershovao.notesapp.R
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.ui.theme.NotesAppTheme
import ru.ershovao.notesapp.utils.TYPE_FIREBASE
import ru.ershovao.notesapp.utils.TYPE_ROOM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.txt_what_use))
            Button(
                onClick = {
                    viewModel.initDB(TYPE_ROOM) {
                        navController.navigate(route = NavRoutes.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
            ) {
                Text(text = stringResource(R.string.txt_room_database))
            }
            Button(
                onClick = {
                    viewModel.initDB(TYPE_FIREBASE) {
                        navController.navigate(route = NavRoutes.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
            ) {
                Text(text = stringResource(R.string.txt_firebase_database))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStart() {
    NotesAppTheme {
        StartScreen(navController = rememberNavController())
    }
}