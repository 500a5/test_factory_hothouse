package soft.divan.test_factory_hothouse.precentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.R
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule
import soft.divan.test_factory_hothouse.precentation.ui.theme.Roboto
import soft.divan.test_factory_hothouse.precentation.util.UiState

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun RegistrationScreenPreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        // RegistrationScreen()
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun RegistrationScreen(
    phone: String,
    navController: NavController,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val isValidUsername = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextRegistration()

        Spacer(modifier = Modifier.height(16.dp))

        TextPhone(phone = phone)

        Spacer(modifier = Modifier.height(16.dp))

        EnterName(name)

        Spacer(modifier = Modifier.height(16.dp))

        UserNickname(userName, isValidUsername)

        TextHelpNickName(isValidUsername)

        Spacer(modifier = Modifier.height(24.dp))

        BtnContinue(isValidUsername, viewModel, userName.value, name.value, phone)
    }


    UiState(viewModel = viewModel, navController = navController)


}

@Composable
private fun TextRegistration() {
    Text(
        text = stringResource(R.string.registration),
        fontSize = 32.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TextPhone(phone: String) {
    Text(
        text = stringResource(R.string.phone_number, phone),
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        color = Color.Gray
    )
}

@Composable
private fun EnterName(name: MutableState<String>) {
    OutlinedTextField(
        value = name.value,
        onValueChange = { name.value = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enter_name)) },
        singleLine = true,
    )
}

@Composable
private fun UserNickname(
    username: MutableState<String>,
    isValidUsername: MutableState<Boolean>
) {
    OutlinedTextField(
        value = username.value,
        onValueChange = {
            username.value = it
            isValidUsername.value = true
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enter_nickname)) },
        isError = !isValidUsername.value,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
        singleLine = true,
    )
}

@Composable
private fun TextHelpNickName(isValidUsername: MutableState<Boolean>) {
    if (!isValidUsername.value) {
        Text(
            text = stringResource(R.string.nickname_input_promt),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun BtnContinue(
    isValidUsername: MutableState<Boolean>,
    viewModel: RegistrationViewModel,
    userName: String,
    name: String,
    phone: String
) {
    Button(
        onClick = {
            isValidUsername.value = checkValidUserName(userName)
            if (isValidUsername.value) {
                viewModel.registrationUser(phone, name, userName)
            }

        },
        modifier = Modifier.fillMaxWidth(),

        ) {
        Text(stringResource(R.string.continue_text))
    }
}

private fun checkValidUserName(userName: String): Boolean {
    return userName.matches(Regex("^[A-Za-z0-9_-]+$")) && userName.length >= 5
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun UiState(
    viewModel: RegistrationViewModel,
    navController: NavController
) {
    when (viewModel.registrationUser.collectAsState().value) {
        is UiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewModel.registrationUser.value as UiState.Error).message,
                Toast.LENGTH_LONG
            ).show()
            viewModel.registrationUser.value = UiState.Empty
        }

        UiState.Empty -> {}

        UiState.Loading -> {
            MyProgressBar()
        }

        is UiState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(BottomItem.Chats)
                viewModel.registrationUser.value = UiState.Empty
            }
        }
    }
}

