package soft.divan.test_factory_hothouse.precentation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import soft.divan.test_factory_hothouse.R


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}

sealed class BottomItem(val title: Int, val iconId: ImageVector, val route: String) {
    data object Chat :
        BottomItem(R.string.chat, Icons.Outlined.Chat, "tab_status")

    data object Profile :
        BottomItem(R.string.profile, Icons.Outlined.PersonOutline, "tab_log")

}

sealed class Route(val route: String) {

    data object Authorization : Route("tab_auth")
    data object Otp : Route("tab_otp")
}


@Composable
fun BottomNavigation(navController: NavController) {
    val listItems = listOf(BottomItem.Chat, BottomItem.Profile)

    NavigationBar() {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(item.route) {
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.iconId,
                        contentDescription = "Icon"
                    )
                },
                label = { Text(text = stringResource(id = item.title)) },

                )
        }

    }
}

@Composable
fun NavGraphs(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination =  Route.Authorization.route
    ) {

        composable(Route.Authorization.route) {
            SelectCountryWithCountryCode(navHostController)
        }

        composable(Route.Otp.route + "/{phoneNumber}") { navBackStackEntry ->
            val phoneNumber = navBackStackEntry.arguments?.getString("phoneNumber")
            phoneNumber?.let { CheckAuthCodeCountryCodeCompose(it, navHostController) }
        }

        composable(BottomItem.Chat.route) {
            //todo
        }
        composable(BottomItem.Profile.route) {
            //todo
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        //bottomBar = { BottomNavigation(navController = navController) }
    ) {

        NavGraphs(navHostController = navController)

    }
}