import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.common.components.BackGround
import com.fcascan.pokeplaymat.presentation.ui.theme.PokeplaymatTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import com.fcascan.pokeplaymat.presentation.common.components.InteractiveCard
import com.fcascan.pokeplaymat.presentation.common.components.LogoButton
import com.fcascan.pokeplaymat.presentation.common.components.RectangularButton
import com.fcascan.pokeplaymat.presentation.common.components.SquareButton

@Composable
fun MainScreen(
    navigateToSettings: () -> Unit = {},
    navigateToGuide: () -> Unit = {},
) {
    val TAG = MainScreen()::class.simpleName
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val artwork = sharedPreferences.getInt("artwork", R.drawable.artwork_stadium)
    val numberOfBenchedCards = sharedPreferences.getInt("numberOfBenchedCards", 5)
    val playerName = sharedPreferences.getString("playerName", "Player") ?: "Player"
    Log.d(TAG, "artwork: $artwork, numberOfBenchedCards: $numberOfBenchedCards, playerName: $playerName")

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BackGround(
            artwork,
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    SquareButton(
                        icon = Icons.Default.Refresh,
                        onClick = { /* TODO: Add action */ }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SquareButton(
                        icon = painterResource(id = R.drawable.btn_sp),
                        onClick = { /* TODO: Add action */ }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        LogoButton(
                            size = DpSize(44.dp, 44.dp),
                            painter = painterResource(id = R.drawable.btn_dice),
                            onClick = { /* TODO: Add action */ }
                        )
                        LogoButton(
                            painter = painterResource(id = R.drawable.btn_gx),
                            onClick = { /* TODO: Add action */ }
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    //Active Card:
                    val damage = remember { mutableIntStateOf(0) }
                    InteractiveCard(
                        damageText = damage.intValue,
                        onTap = {
                            //add 10 damage:
                            damage.intValue += 10
                        },
                        onDoubleTap = {
                            //reduce 10 damage:
                            damage.intValue = maxOf(0, damage.intValue - 10)
                        },
                        onLongPress = {
                            //TODO: Open card dialog
                        },
                        onPositiveSwipe = {
                            //add 10 damage:
                            damage.intValue += 10
                        },
                        onNegativeSwipe = {
                            //reduce 10 damage:
                            damage.intValue = maxOf(0, damage.intValue - 10)
                        }
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    LogoButton(
                        painter = painterResource(id = R.drawable.btn_coin),
                        onClick = { /* TODO: Add action */ }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    RectangularButton(
                        text = playerName,
                        onClick = { /* TODO: Add action */ }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        SquareButton(
                            icon = painterResource(R.drawable.btn_book),
                            onClick = { navigateToGuide() }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        SquareButton(
                            icon = Icons.Default.Settings,
                            onClick = { navigateToSettings() }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                //Benched Cards:
                val damageArray = List(numberOfBenchedCards) { remember { mutableIntStateOf(0) } }
                for (i in 0 until numberOfBenchedCards) {
                    InteractiveCard(
                        damageText = damageArray[i].intValue,
                        onTap = {
                          //add 10 damage:
                            damageArray[i].intValue += 10
                        },
                        onDoubleTap = {
                            //reduce 10 damage:
                            damageArray[i].intValue = maxOf(0, damageArray[i].intValue - 10)
                        },
                        onLongPress = {
                          //TODO: Open card dialog
                        },
                        onPositiveSwipe = {
                            //add 10 damage:
                            damageArray[i].intValue += 10
                        },
                        onNegativeSwipe = {
                            //reduce 10 damage:
                            damageArray[i].intValue = maxOf(0, damageArray[i].intValue - 10)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 360)
@Composable
fun MainScreenPreview() {
    PokeplaymatTheme {
        MainScreen(
            navigateToSettings = {},
            navigateToGuide = {}
        )
    }
}