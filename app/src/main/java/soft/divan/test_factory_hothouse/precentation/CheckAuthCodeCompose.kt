package soft.divan.test_factory_hothouse.precentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun CheckAuthCodeComposePreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }){
        CheckAuthCodeCountryCodeCompose ()
    }
}


@Composable
fun CheckAuthCodeCountryCodeCompose(
    viewModel: CheckAuthCodeViewModel = koinViewModel()

) {
Text(text = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww", color = Color.Red)
}