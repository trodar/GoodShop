package com.trodar.catalog.product_card

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.animateBounds
import com.skydoves.orbital.rememberMovableContentOf
import com.trodar.catalog.R
import com.trodar.catalog.catalog_list.CatalogRoute
import com.trodar.model.Card
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants
import com.trodar.utils.extensions.canGoBack
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.fragments.CenterAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListRoute(
    navController: NavHostController,
    cardListViewModel: CardListViewModel = hiltViewModel()
) {

    val uiState by cardListViewModel.uiState.collectAsState()


    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(com.trodar.theme.R.string.payment_method),
                navigationIconContent = {
                    BackIconButton {
                        if (navController.canGoBack)
                            navController.popBackStack()
                    }
                }
            )

        }

    ) { paddingValues ->

        CardsScreen(
            paddingValues = paddingValues,
            cards = uiState.cards,
        )
    }


}

@Composable
fun CardsScreen(
    paddingValues: PaddingValues,
    cards: List<Card>,

    ) {
    var selectedOption by remember { mutableStateOf(cards[0].id) }

    Orbital(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        LazyColumn {
            items(cards, key = { it.id }) { card ->
                var expanded by rememberSaveable { mutableStateOf(false) }

                Spacer(modifier = Modifier.height(Constants.SPACER8))
                AnimatedVisibility(
                    remember { MutableTransitionState(false) }
                        .apply { targetState = true },
                    enter = fadeIn(),
                ) {
                    Orbital(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded = !expanded
                        }
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )) {
                        val title = rememberMovableContentOf {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .animateBounds(
                                        if (expanded) {
                                            Modifier.height(45.dp)
                                        } else {
                                            Modifier.height(80.dp)
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = card.id,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    Text(
                                        text = String.format("%d/%d", card.extMonth, card.expYear),
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 12.sp,
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = { }) {
                                    Icon(
                                        painter = painterResource(R.drawable.delete_forever_24),
                                        contentDescription = "Delete",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .animateBounds(
                                                if (expanded) {
                                                    Modifier.size(32.dp)
                                                } else {
                                                    Modifier.size(0.dp)
                                                }
                                            ),
                                    )
                                }
                            }
                        }
                        val image = rememberMovableContentOf {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (card.id == selectedOption),
                                    onClick = { selectedOption = card.id }
                                )
                                Image(
                                    painter = painterResource(card.image),

                                    contentDescription = "image",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .animateBounds(
                                            if (expanded) {
                                                Modifier.fillMaxWidth()
                                            } else {
                                                Modifier.size(80.dp)
                                            },
                                            spring(stiffness = Spring.StiffnessLow),
                                        )
                                        .clip(RoundedCornerShape(5.dp))
                                        .size(200.dp, 200.dp)
                                )
                            }
                        }

                        if (expanded) {
                            Column {
                                image()
                                title()
                            }
                        } else {
                            Row {
                                image()
                                title()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(
    "CardsScreenPreview screen",
    showBackground = true,
    device = Devices.NEXUS_6
)
@Preview(
    "CardsScreenPreview screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_6,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CardsScreenPreview() {
    FakeShopTheme {
        CardsScreen(
            paddingValues = PaddingValues(),
            cards = listOf(
                Card(
                    id = "5678 1234 6543 0987",
                    image = com.trodar.data.R.drawable.visa,
                    extMonth = 2,
                    expYear = 2029,
                    cvcCode = 121,
                ),
                Card(
                    id = "5678 1234 6543 3456",
                    image = com.trodar.data.R.drawable.master_card,
                    extMonth = 3,
                    expYear = 2029,
                    cvcCode = 122,
                ),
                Card(
                    id = "5678 1234 6543 7890",
                    image = com.trodar.data.R.drawable.apple_pay,
                    extMonth = 4,
                    expYear = 2029,
                    cvcCode = 123,
                ),
            )
        )
    }
}