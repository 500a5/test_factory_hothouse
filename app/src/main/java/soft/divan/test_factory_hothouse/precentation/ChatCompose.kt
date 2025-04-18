package soft.divan.test_factory_hothouse.precentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.KoinApplication
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule
import soft.divan.test_factory_hothouse.data.remote.entity.Message
import soft.divan.test_factory_hothouse.data.util.UtilData
import soft.divan.test_factory_hothouse.precentation.ui.theme.Roboto
import androidx.compose.material3.Text

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun ChatComposePreview() {
    KoinApplication(application = { modules(presentationModule, dataModule, domainModule) }) {
        ChatScreen("1")
    }
}

@Composable
fun ChatScreen(idChat: String) {
    Scaffold(topBar = {
        TopBar(text = UtilData.listChats.first { it.id == idChat }.name)
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {

            Spacer(modifier = Modifier.weight(1f))
            MessageList(
                modifier = Modifier.weight(1f),
                idChat
            )
            MessageInput()
        }
    }
}

@Composable
private fun TopBar(text: String) {
    Text(
        text = text,
        fontSize = 32.sp,
        fontFamily = Roboto,
        color = Color.Black,
        modifier = Modifier
            .padding(16.dp),

        )
    HorizontalDivider()
}

@Composable
fun MessageInput(

) {
    var inputValue by remember { mutableStateOf("") }

    fun sendMessage() {
        inputValue = ""
    }

    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            modifier = Modifier.weight(1f),
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions { sendMessage() },
        )
        Button(
            modifier = Modifier.height(56.dp),
            onClick = { sendMessage() },
            enabled = inputValue.isNotBlank(),
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "send"
            )
        }
    }
}


@Composable
fun MessageList(

    modifier: Modifier = Modifier,
    idChat: String
    ) {


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            reverseLayout = true,
        ) {
            items(UtilData.listChats.first { it.id == idChat }.messages) { message ->
                MessageCard(message)
            }
        }
    }
}


@Composable
fun MessageCard(messageItem: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when {
            messageItem.isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageItem),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    messageItem.isMine -> Color.Magenta
                    else -> Color.Gray
                }, //Card background color
                contentColor = Color.White  //Card content color,e.g.text
            )


        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = messageItem.text,
                fontSize = 24.sp,
                fontFamily = Roboto,
                color = Color.Black

            )
        }

    }
}

@Composable
fun cardShapeFor(message: Message): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}

