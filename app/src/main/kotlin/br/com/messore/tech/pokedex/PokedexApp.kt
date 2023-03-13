package br.com.messore.tech.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.messore.tech.pokedex.navigation.AppNavHost
import br.com.messore.tech.pokedex.navigation.BottomNavigation
import br.com.messore.tech.pokedex.ui.theme.PokedexTheme

@Composable
fun PokedexApp() {
    PokedexTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavigation(navController = navController) }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}
