import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.model.CustomThemeSelection
import com.fcascan.pokeplaymat.presentation.common.components.BackGround
import com.fcascan.pokeplaymat.presentation.common.components.InteractiveCard
import com.fcascan.pokeplaymat.presentation.common.components.LogoButton
import com.fcascan.pokeplaymat.presentation.common.components.RectangularButton
import com.fcascan.pokeplaymat.presentation.common.components.SquareButton
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size
import com.fcascan.pokeplaymat.presentation.ui.dimen.Spacing
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme
import com.fcascan.pokeplaymat.presentation.util.CustomThemeSelectionMapper
import com.fcascan.pokeplaymat.presentation.viewmodel.MainScreenState
import com.fcascan.pokeplaymat.presentation.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navigateToSettings: () -> Unit,
    navigateToGuide: () -> Unit,
) {
    val viewModel : MainScreenViewModel = hiltViewModel()
    val mainScreenState by viewModel.mainScreenState.collectAsStateWithLifecycle()

    when(mainScreenState) {
        is MainScreenState.Loading -> {
            //TODO
            // Show loading state if needed
        }
        is MainScreenState.Error -> {
            //TODO
            // Handle error state if needed
        }
        is MainScreenState.Success -> {
            MainScreenContent(
                customThemeSelected = (mainScreenState as MainScreenState.Success).customTheme,
                playerName = (mainScreenState as MainScreenState.Success).playerName,
                numberOfBenchedCards = (mainScreenState as MainScreenState.Success).numberOfBenchedCards,
                navigateToSettings = navigateToSettings,
                navigateToGuide = navigateToGuide
            )
        }
    }
}

@Composable
fun MainScreenContent(
    customThemeSelected: CustomThemeSelection?,
    playerName: String?,
    numberOfBenchedCards: Int,
    navigateToSettings: () -> Unit,
    navigateToGuide: () -> Unit
) {
    val customThemeSelectionMapper = CustomThemeSelectionMapper

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        BackGround(
            customThemeSelectionMapper.mapToDrawableResId(customThemeSelected),
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.Small),
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
                    Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
                    SquareButton(
                        icon = painterResource(id = R.drawable.btn_sp),
                        onClick = { /* TODO: Add action */ }
                    )
                }
                Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        LogoButton(
                            size = DpSize(Size.MediumPlus, Size.MediumPlus),
                            painter = painterResource(id = R.drawable.btn_dice),
                            onClick = { /* TODO: Add action */ }
                        )
                        LogoButton(
                            painter = painterResource(id = R.drawable.btn_gx),
                            onClick = { /* TODO: Add action */ }
                        )
                    }
                    Spacer(modifier = Modifier.width(Spacing.Large))
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
                    Spacer(modifier = Modifier.width(Spacing.Large))
                    LogoButton(
                        painter = painterResource(id = R.drawable.btn_coin),
                        onClick = { /* TODO: Add action */ }
                    )
                }
                Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    RectangularButton(
                        text = playerName ?: "Player",
                        onClick = { /* TODO: Add action */ }
                    )
                    Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
                    Row {
                        SquareButton(
                            icon = painterResource(R.drawable.btn_book),
                            onClick = { navigateToGuide() }
                        )
                        Spacer(modifier = Modifier.width(Spacing.ExtraSmall))
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
                    .padding(Padding.ExtraLarge),
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

@Preview(
    showBackground = true,
    widthDp = 700,
    heightDp = 360,
)
@Composable
private fun MainScreenLandscapePreview() {
    StadiumTheme {
        MainScreenContent(
            customThemeSelected = CustomThemeSelection.RIVER,
            playerName = "Player",
            numberOfBenchedCards = 5,
            navigateToSettings = {},
            navigateToGuide = {},
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 360,
    heightDp = 700,
)
@Composable
private fun MainScreenNormalPreview() {
    StadiumTheme {
        MainScreenContent(
            customThemeSelected = CustomThemeSelection.RIVER,
            playerName = "Player",
            numberOfBenchedCards = 5,
            navigateToSettings = {},
            navigateToGuide = {},
        )
    }
}
