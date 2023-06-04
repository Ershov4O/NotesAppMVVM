package ru.ershovao.notesapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.navigation.NoteNavHost
import ru.ershovao.notesapp.ui.theme.NotesAppTheme
import ru.ershovao.notesapp.utils.DB_TYPE

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(applicationContext as Application))
                val navController = rememberNavController()
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Notes app")
                                if (DB_TYPE.value.isNotEmpty()) {
                                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "", modifier = Modifier.clickable {
                                        viewModel.signOut {
                                            navController.navigate(NavRoutes.Start.route) {
                                                popUpTo(NavRoutes.Start.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    })
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = androidx.compose.ui.graphics.Color.Black
                        )
                    )
                }, content = { contentPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) {
                        NoteNavHost(viewModel, navController)
                    }
                })
            }
        }
    }
}