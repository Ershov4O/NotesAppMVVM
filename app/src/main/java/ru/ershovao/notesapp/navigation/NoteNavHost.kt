package ru.ershovao.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.screens.AddScreen
import ru.ershovao.notesapp.screens.MainScreen
import ru.ershovao.notesapp.screens.NoteScreen
import ru.ershovao.notesapp.screens.StartScreen

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
            StartScreen(navController = navController)
        }
        composable(NavRoutes.Main.route) {
            MainScreen(navController = navController)
        }
        composable(NavRoutes.Add.route) {
            AddScreen(navController = navController)
        }
        composable(NavRoutes.Note.route) {
            NoteScreen(navController = navController)
        }

    }
}