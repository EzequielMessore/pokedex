package br.com.messore.tech.pokedex.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.messore.tech.pokedex.R
import br.com.messore.tech.pokedex.pokelist.PokeListRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = BottomNavItem.Pokedex.route,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(BottomNavItem.Pokedex.route) {
            PokeListRoute()
        }

        composable(BottomNavItem.Regions.route) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.teal_700))
                    .fillMaxSize()
            )
        }

        composable(BottomNavItem.Favorite.route) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.purple_700))
                    .fillMaxSize()
            )
        }

        composable(BottomNavItem.Profile.route) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.purple_200))
                    .fillMaxSize()
            )
        }
    }
}
