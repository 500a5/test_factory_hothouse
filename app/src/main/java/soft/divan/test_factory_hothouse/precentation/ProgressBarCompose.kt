package soft.divan.test_factory_hothouse.precentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun MyProgressBarPreview(){
    MyProgressBar()
}
@Composable
fun MyProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray.copy(alpha = 0.5f)).clickable(enabled = false){},
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = Color(0xffce00ff),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(48.dp)
        )
    }
}