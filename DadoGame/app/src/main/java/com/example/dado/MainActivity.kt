package com.example.dado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadogameApp()
        }
    }
}

@Composable
fun DadogameApp() {
    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var totalRolls by remember { mutableStateOf(0) }
    var wins by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") }


    Button(onClick = {
        dice1 = Random.nextInt(1, 7)
        dice2 = Random.nextInt(1, 7)
        totalRolls++

        val sum = dice1 + dice2
        val won = sum == 7 || sum == 11

        if (won) {
            wins++
            message = "Você venceu!"
        } else {
            message = "Tente novamente!"
        }
    }) {
        Text("girar dado")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = getDiceImage(dice1)),
                contentDescription = "Dice 1"
            )
            Image(
                painter = painterResource(id = getDiceImage(dice2)),
                contentDescription = "Dice 2"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message)
        Spacer(modifier = Modifier.height(8.dp))
        val winPercentage = if (totalRolls > 0) (wins * 100 / totalRolls) else 0
        Text(text = "Score: $wins/$totalRolls = $winPercentage%")
    }
}

private fun getDiceImage(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}
