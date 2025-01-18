package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) { // Corrected Surface import
                    LemonadeApp(
                        modifier = Modifier // Added padding directly here
                    )
                }
            }
        }
    }
}



@SuppressLint("RememberReturnType")
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var instruction: String by remember {
         mutableStateOf("Pick a lemon from the tree")
    }

    var imageNumber: Int by remember {
        mutableIntStateOf(1)
    }

    var trialCounter: Int by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier // Corrected to use the passed modifier
    ) {
            Card(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),

                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Lemon)),
                shape = RoundedCornerShape(0.dp)
            ) {

        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),

            text = "Lemonade \uD83C\uDF4B",
            style = TextStyle(
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            ),
        )

        Spacer(modifier = Modifier.height(36.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 84.dp)
                .padding(4.dp),

            border = BorderStroke(1.dp, colorResource(R.color.Lemon))
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = instruction,
                style = TextStyle(
                    fontSize = 20.sp

                )
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if ((2..4).random()!=3){
                            trialCounter +=1
                             instruction = "Try Again! You will get it!\n\n Trial Count $trialCounter"


                        }
                        else{
                            trialCounter = 0
                            when (imageNumber) {
                                1 -> {
                                    instruction = "Now Squeeze the lemon"
                                    imageNumber = 2
                                }
                                2 -> {
                                    instruction = "Drink the lemonade"
                                    imageNumber = 3
                                }
                                3 -> {
                                    instruction = "You drank the whole lemonade"
                                    imageNumber = 4
                                }
                                else -> {
                                    instruction = "Pick a lemon from the fruit again"
                                    imageNumber = 1
                                }
                            }
                        }

                    },
                painter = painterResource(
                    when(imageNumber){
                        1 -> R.drawable.lemon_tree
                        2 -> R.drawable.lemon_squeeze
                        3 -> R.drawable.lemon_drink
                        4 -> R.drawable.lemon_restart
                        else ->  R.drawable.lemon_tree
                    }
                ),
                contentDescription = "A lemon tree"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme { // Wrap in theme to match the actual app look
        LemonadeApp()
    }
}
