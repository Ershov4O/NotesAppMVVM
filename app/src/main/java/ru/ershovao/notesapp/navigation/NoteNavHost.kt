package ru.ershovao.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.MainViewModel
import ru.ershovao.notesapp.screens.AddScreen
import ru.ershovao.notesapp.screens.MainScreen
import ru.ershovao.notesapp.screens.NoteScreen
import ru.ershovao.notesapp.screens.StartScreen
import ru.ershovao.notesapp.utils.Constants
import ru.ershovao.notesapp.utils.Constants.Screens.ADD_SCREEN
import ru.ershovao.notesapp.utils.Constants.Screens.MAIN_SCREEN
import ru.ershovao.notesapp.utils.Constants.Screens.NOTE_SCREEN
import ru.ershovao.notesapp.utils.Constants.Screens.START_SCREEN

sealed class NavRoutes(val route: String) {
    object Start : NavRoutes(START_SCREEN)
    object Main : NavRoutes(MAIN_SCREEN)
    object Add : NavRoutes(ADD_SCREEN)
    object Note : NavRoutes(NOTE_SCREEN)
}

@Composable
fun NoteNavHost(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Start.route) {
        composable(NavRoutes.Start.route) {
            StartScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.Add.route) {
            AddScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.Note.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = viewModel, noteId = backStackEntry.arguments?.getString(Constants.Keys.ID))
        }

    }
}