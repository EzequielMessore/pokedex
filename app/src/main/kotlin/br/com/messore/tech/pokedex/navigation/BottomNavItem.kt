package br.com.messore.tech.pokedex.navigation

import br.com.messore.tech.pokedex.R

sealed class BottomNavItem(
    val icon: Int,
    val title: String,
    val route: String,
    val unselectedIcon: Int,
) {
    data object Pokedex : BottomNavItem(
        title = "Pokédex",
        route = "pokedex",
        icon = R.drawable.ic_pokeball,
        unselectedIcon = R.drawable.ic_pokeball_unselected
    )

    data object Regions : BottomNavItem(
        title = "Regiões",
        route = "regions",
        icon = R.drawable.ic_regions,
        unselectedIcon = R.drawable.ic_regions_unselected
    )

    data object Favorite : BottomNavItem(
        title = "Favoritos",
        route = "favorite",
        icon = R.drawable.ic_favorite,
        unselectedIcon = R.drawable.ic_favorite_unselected
    )

    data object Profile : BottomNavItem(
        title = "Conta",
        route = "profile",
        icon = R.drawable.ic_profile,
        unselectedIcon = R.drawable.ic_profile_unselected
    )
}
