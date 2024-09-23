package soft.divan.test_factory_hothouse.precentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun ProfileScreenPreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        //SelectCountryWithCountryCode()
    }
}


@Composable
fun ProfileScreen(

) {

}