package soft.divan.test_factory_hothouse.precentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simon.xmaterialccp.component.MaterialCountryCodePicker
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.simon.xmaterialccp.data.utils.checkPhoneNumber
import com.simon.xmaterialccp.data.utils.getDefaultLangCode
import com.simon.xmaterialccp.data.utils.getDefaultPhoneCode
import com.simon.xmaterialccp.data.utils.getLibCountries

import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.R
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.precentation.ui.theme.Roboto

import soft.divan.test_factory_hothouse.app.di.presentationModule
import soft.divan.test_factory_hothouse.precentation.util.UiState


@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun AuthorizationComposePreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        //SelectCountryWithCountryCode()
    }
}


@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun SelectCountryWithCountryCode(
    navController: NavController,
    viewModel: AuthorizationViewModel = koinViewModel()

) {

    val context = LocalContext.current
    val phoneCode = remember { mutableStateOf(getDefaultPhoneCode(context)) }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val defaultLang = rememberSaveable { mutableStateOf(getDefaultLangCode(context)) }
    val isValidPhone = remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextPhone()

        Spacer(modifier = Modifier.height(16.dp))

        TextHelp()

        Spacer(modifier = Modifier.height(24.dp))

        CountryCodePicker(phoneCode, defaultLang, isValidPhone, phoneNumber)

        Spacer(modifier = Modifier.height(24.dp))

        ButtonNext(
            isValidPhone, phoneNumber, phoneCode.value, defaultLang.value, viewModel
        )


    }

    UiState(viewModel, phoneCode, phoneNumber, navController)
}


@Composable
private fun TextPhone() {
    Text(
        text = stringResource(R.string.phone),
        fontSize = 32.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TextHelp() {
    Text(
        text = stringResource(R.string.help_text_auth),
        fontSize = 16.sp,
        fontFamily = Roboto,
        color = Color.Gray,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}


@Composable
private fun CountryCodePicker(
    phoneCode: MutableState<String>,
    defaultLang: MutableState<String>,
    isValidPhone: MutableState<Boolean>,
    phoneNumber: MutableState<String>,

    ) {
    val maxLengthPhoneNumber = 10
    MaterialCountryCodePicker(
        pickedCountry = {
            phoneCode.value = it.countryPhoneCode
            defaultLang.value = it.countryCode
        },
        defaultCountry = getLibCountries().single { it.countryCode == defaultLang.value },
        error = !isValidPhone.value,
        text = phoneNumber.value,
        onValueChange = {
            if (it.length <= maxLengthPhoneNumber) phoneNumber.value = it
            isValidPhone.value = true
        },
        searchFieldPlaceHolderTextStyle = TextStyle(fontFamily = Roboto),
        searchFieldTextStyle = TextStyle(fontFamily = Roboto),
        phonenumbertextstyle = TextStyle(fontFamily = Roboto),
        countrytextstyle = TextStyle(fontFamily = Roboto),
        countrycodetextstyle = TextStyle(fontFamily = Roboto),
        showErrorText = true,
        showCountryCodeInDIalog = true,
        showDropDownAfterFlag = true,
        appbartitleStyle = TextStyle(fontFamily = Roboto),
        showCountryFlag = true,
        showCountryCode = true,
        isEnabled = true,
        colors = ccpDefaultColors(),
        phonehintnumbertextstyle = TextStyle(fontFamily = Roboto, color = Color.Gray),
        errorTextStyle = TextStyle(fontFamily = Roboto),
        dialogcountrycodetextstyle = TextStyle(fontFamily = Roboto)
    )
}

@Composable
private fun ButtonNext(
    isValidPhone: MutableState<Boolean>,
    phoneNumber: MutableState<String>,
    phoneCode: String,
    defaultLang: String,
    viewModel: AuthorizationViewModel
) {
    OutlinedButton(
        onClick = {
            val fullPhoneNumber = "$phoneCode${phoneNumber.value}"
            isValidPhone.value = checkPhoneNumber(
                phone = phoneNumber.value,
                fullPhoneNumber = fullPhoneNumber,
                countryCode = defaultLang
            )
            if (isValidPhone.value) {
                viewModel.sendAuthCode(fullPhoneNumber)
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = stringResource(R.string.next))
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun UiState(
    viewModel: AuthorizationViewModel,
    phoneCode: MutableState<String>,
    phoneNumber: MutableState<String>,
    navController: NavController
) {
    when (viewModel.sendAuthCode.collectAsState().value) {
        is UiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewModel.sendAuthCode.value as UiState.Error).message,
                Toast.LENGTH_LONG
            ).show()
            viewModel.sendAuthCode.value = UiState.Init
        }

        UiState.Init -> {}

        UiState.Loading -> {
            MyProgressBar()
        }

        is UiState.Success -> {
            LaunchedEffect(Unit) {
                val fullPhoneNumber = "${phoneCode.value}${phoneNumber.value}"
                navController.navigate(Route.Otp.route + "/${fullPhoneNumber}")
                viewModel.sendAuthCode.value = UiState.Init
            }
        }
    }
}

