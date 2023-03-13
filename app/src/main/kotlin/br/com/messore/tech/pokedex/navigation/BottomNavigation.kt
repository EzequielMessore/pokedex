package br.com.messore.tech.pokedex.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.messore.tech.pokedex.extensions.getCurrentRoute

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Pokedex,
        BottomNavItem.Regions,
        BottomNavItem.Favorite,
        BottomNavItem.Profile,
    )

    NavigationBar {
        items.forEach { item ->
            NavigationItem(item = item, navController = navController)
        }
    }
}

@Composable
fun RowScope.NavigationItem(item: BottomNavItem, navController: NavController) {
    val selected = navController.getCurrentRoute() == item.route

    NavigationBarItem(
        selected = selected,
        alwaysShowLabel = false,
        onClick = navigate(item, navController),
        label = { Text(text = item.title, fontSize = 9.sp) },
        icon = {
            val icon = if (selected) item.icon else item.unselectedIcon
            Image(painter = painterResource(id = icon), contentDescription = item.title)
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)
        )
    )
}

@Composable
fun navigate(item: BottomNavItem, navController: NavController): (() -> Unit) = {
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
