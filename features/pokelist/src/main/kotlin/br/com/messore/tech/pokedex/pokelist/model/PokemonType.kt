package br.com.messore.tech.pokedex.pokelist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.messore.tech.pokedex.pokelist.R

@Suppress("MagicNumber")
sealed class PokemonType(@StringRes val text: Int, val color: Color, @DrawableRes val iconResource: Int) {
    object Normal : PokemonType(R.string.type_normal, Color(0xff919aa2), R.drawable.ic_type_normal)
    object Fire : PokemonType(R.string.type_fire, Color(0xffff9d55), R.drawable.ic_type_fire)
    object Water : PokemonType(R.string.type_water, Color(0xff5090d6), R.drawable.ic_type_water)
    object Electric : PokemonType(R.string.type_electric, Color(0xfff4d23c), R.drawable.ic_type_electric)
    object Grass : PokemonType(R.string.type_grass, Color(0xff63bc5a), R.drawable.ic_type_grass)
    object Ice : PokemonType(R.string.type_ice, Color(0xff73cec0), R.drawable.ic_type_ice)
    object Fighting : PokemonType(R.string.type_fighting, Color(0xffce416b), R.drawable.ic_type_fighting)
    object Poison : PokemonType(R.string.type_poison, Color(0xffb567ce), R.drawable.ic_type_poison)
    object Ground : PokemonType(R.string.type_ground, Color(0xffd97845), R.drawable.ic_type_ground)
    object Flying : PokemonType(R.string.type_flying, Color(0xff89aae3), R.drawable.ic_type_flying)
    object Psychic : PokemonType(R.string.type_psychic, Color(0xfffa7179), R.drawable.ic_type_psychic)
    object Bug : PokemonType(R.string.type_bug, Color(0xff91c12f), R.drawable.ic_type_bug)
    object Rock : PokemonType(R.string.type_rock, Color(0xffc5b78c), R.drawable.ic_type_rock)
    object Ghost : PokemonType(R.string.type_ghost, Color(0xff5269ad), R.drawable.ic_type_ghost)
    object Dragon : PokemonType(R.string.type_dragon, Color(0xff0b6dc3), R.drawable.ic_type_dragon)
    object Dark : PokemonType(R.string.type_dark, Color(0xff5a5465), R.drawable.ic_type_dark)
    object Steel : PokemonType(R.string.type_steel, Color(0xff5a8ea2), R.drawable.ic_type_steel)
    object Fairy : PokemonType(R.string.type_fairy, Color(0xffec8fe6), R.drawable.ic_type_fairy)
}
