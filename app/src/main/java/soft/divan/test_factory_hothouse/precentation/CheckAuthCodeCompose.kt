package soft.divan.test_factory_hothouse.precentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.composeuisuite.ohteepee.configuration.OhTeePeeCellConfiguration
import com.composeuisuite.ohteepee.configuration.OhTeePeeConfigurations
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.R
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule
import soft.divan.test_factory_hothouse.precentation.ui.theme.Roboto

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun CheckAuthCodeComposePreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        //CheckAuthCodeCountryCodeCompose("+79606277700")
    }
}


@Composable
fun CheckAuthCodeCountryCodeCompose(
    phone: String,
    navController: NavController,
    viewModel: CheckAuthCodeViewModel = koinViewModel(),


    ) {

    val otpCode = remember { mutableStateOf("") }


    if (otpCode.value.filter { !it.isWhitespace() }.length == 6)
        viewModel.checkAuthCode(phone, otpCode.value)


    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Column {


            TextEnterOtp()
            Spacer(modifier = Modifier.height(16.dp))
            TextHelp(phone)

            Spacer(modifier = Modifier.height(24.dp))
            Otp(otpCode)

        }
    }
}


@Composable
private fun TextEnterOtp() {
    Text(
        text = stringResource(R.string.enter_code),
        fontSize = 32.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TextHelp(phone: String) {
    Text(
        text = stringResource(R.string.sms_help, phone),
        fontSize = 16.sp,
        fontFamily = Roboto,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
    )
}


@Composable
private fun Otp(otpValue: MutableState<String>) {
    val defaultCellConfig = OhTeePeeCellConfiguration.withDefaults(
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(
            color = Color.Black
        )
    )

    OhTeePeeInput(
        value = otpValue.value,
        onValueChange = { newValue, isValid ->
            otpValue.value = newValue
        },
        configurations = OhTeePeeConfigurations.withDefaults(
            cellsCount = 6,
            emptyCellConfig = defaultCellConfig,
            cellModifier = Modifier
                .padding(horizontal = 4.dp)
                .size(48.dp),
        ), modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
}