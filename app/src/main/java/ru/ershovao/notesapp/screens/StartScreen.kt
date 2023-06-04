package ru.ershovao.notesapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.ershovao.notesapp.MainViewModel
import ru.ershovao.notesapp.MainViewModelFactory
import ru.ershovao.notesapp.R
import ru.ershovao.notesapp.navigation.NavRoutes
import ru.ershovao.notesapp.ui.theme.NotesAppTheme
import ru.ershovao.notesapp.utils.DB_TYPE
import ru.ershovao.notesapp.utils.LOGIN
import ru.ershovao.notesapp.utils.PASSWORD
import ru.ershovao.notesapp.utils.TYPE_FIREBASE
import ru.ershovao.notesapp.utils.TYPE_ROOM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    if (openBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                openBottomSheet.value = false
            },
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Log in",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = "Login") },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        isError = password.isEmpty()
                    )
                    Button(
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDB(TYPE_FIREBASE) {
                                DB_TYPE = TYPE_FIREBASE
                                navController.navigate(route = NavRoutes.Main.route)
                                scope.launch {
                                    bottomSheetState.hide()
                                }.invokeOnCompletion {
                                    if (!bottomSheetState.isVisible) {
                                        openBottomSheet.value = false
                                    }
                                }
                            }

                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty(),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Sign in")
                    }
                }
            }
        }
    }
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
                        DB_TYPE = TYPE_ROOM
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
                    openBottomSheet.value = true

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
        val context = LocalContext.current
        val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}