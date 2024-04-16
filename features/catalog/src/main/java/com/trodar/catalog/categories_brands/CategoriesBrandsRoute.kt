package com.trodar.catalog.categories_brands

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.trodar.theme.R
import com.trodar.utils.extensions.canGoBack
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.fragments.CenterAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesBrandsRoute(
    navController: NavHostController,
    categoriesBrandsViewModel: CategoriesBrandsViewModel = hiltViewModel()
) {

    val uiState by categoriesBrandsViewModel.uiState.collectAsState()
    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(R.string.brands),
                navigationIconContent = {
                    BackIconButton {
                        if (navController.canGoBack)
                            navController.popBackStack()
                    }
                }
            )
        }

    ) { paddingValues ->
        if (uiState.isLoading) {
            CustomCircularProgressBar()
        } else {
            CategoriesBrandsScreen(
                paddingValues = paddingValues,
                uiState.categories,
                uiState.brands
            )
        }
    }
}