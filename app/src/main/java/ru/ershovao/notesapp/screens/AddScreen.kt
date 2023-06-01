package ru.ershovao.notesapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController) {
    var title by remember {
        mutableStateOf("")
    }
    var subTitle by remember {
        mutableStateOf("")
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
                onValueChange = { title = it },
                label = { Text(text = "Note title") }
            )
            OutlinedTextField(
                value = subTitle,
                onValueChange = { subTitle = it },
                label = { Text(text = "Note subtitle") }
            )

            Button(modifier = Modifier
                .width(200.dp)
                .padding(vertical = 32.dp),
                onClick = {
                    navController.navigate(NavRoutes.Main.route)
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
        AddScreen(navController = rememberNavController())
    }
}