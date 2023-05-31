package ru.ershovao.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.screens.Add
import ru.ershovao.notesapp.screens.Main
import ru.ershovao.notesapp.screens.Note
import ru.ershovao.notesapp.screens.Start

sealed class NavRoutes(val route: String) {
    object Start : NavRoutes("start_screen")
    object Main : NavRoutes("main_screen")
    object Add : NavRoutes("add_screen")
    object Note : NavRoutes("note_screen")
}

@Composable
fun NoteNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Start.route) {
        composable(NavRoutes.Start.route) {
            Start(navController = navController)
        }
        composable(NavRoutes.Main.route) {
            Main(navController = navController)
        }
        composable(NavRoutes.Add.route) {
            Add(navController = navController)
        }
        composable(NavRoutes.Note.route) {
            Note(navController = navController)
        }

    }
}