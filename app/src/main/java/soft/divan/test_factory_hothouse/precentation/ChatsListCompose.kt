package soft.divan.test_factory_hothouse.precentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import soft.divan.test_factory_hothouse.data.entity.Chat
import soft.divan.test_factory_hothouse.data.entity.Message
import soft.divan.test_factory_hothouse.data.util.UtilData
import soft.divan.test_factory_hothouse.precentation.ui.theme.Roboto

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun ChatsListComposePreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        //ChatsListScreen()
    }
}

@Composable
fun ChatsListScreen(navController: NavController) {


    Scaffold(topBar = {
        TopBar()
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(UtilData.listChats) { channel ->
                    ChannelListItem(
                        chat = channel,
                        onClick = { navController.navigate(Route.Chat.route + "/${channel.id}") },
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    Text(
        text = "Чаты", fontSize = 32.sp,
        fontFamily = Roboto,
        color = Color.Black,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun ChannelListItem(
    chat: Chat,
    onClick: (chat: Chat) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onClick(chat) }
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(imageVector = Icons.Default.Person, contentDescription = "")

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = chat.name,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
            )

            val lastMessageText = chat.messages.firstOrNull()?.text ?: "..."
            Text(
                text = lastMessageText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}