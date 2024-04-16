package com.trodar.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.components.FindIconButton
import com.trodar.utils.fetures.fragments.LeftAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit,
){
    val uiState by homeViewModel.uiState.collectAsState()
    val name = uiState.profile.user.split(" ").first()
    val selectedProduct by homeViewModel.selectedProducts.collectAsStateWithLifecycle()

    Scaffold(

        topBar = {
            LeftAppBar(
                title = stringResource(id = com.trodar.theme.R.string.home_welcome, name),
                actions = {
                    FindIconButton(
                        onClick = {}
                    )
                }
            )
        }

    ) { paddingValues ->

        if (uiState.isLoading) {
            CustomCircularProgressBar()
        } else {
            HomeScreen(
                paddingValues = paddingValues,
                bannerProducts = uiState.topProducts,
                products = uiState.products,
                selectedProduct =  selectedProduct,
                onProductClick = onProductClick,
                onAddCartClick =  homeViewModel::toggleProductSelection,
            )
        }
    }
}