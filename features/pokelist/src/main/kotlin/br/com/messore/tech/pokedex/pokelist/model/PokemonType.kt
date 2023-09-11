package br.com.messore.tech.pokedex.pokelist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.messore.tech.pokedex.pokelist.R
import br.com.messore.tech.pokedex.domain.model.PokemonType as Type

@Suppress("MagicNumber")
sealed class PokemonType(
    @StringRes val text: Int,
    color: Long,
    @DrawableRes val iconResource: Int,
    val originalType: Type
) {
    val color = Color(color)

    data object Normal : PokemonType(R.string.type_normal, 0xff919aa2, R.drawable.ic_type_normal, Type.NORMAL)
    data object Fire : PokemonType(R.string.type_fire, 0xffff9d55, R.drawable.ic_type_fire, Type.FIRE)
    data object Water : PokemonType(R.string.type_water, 0xff5090d6, R.drawable.ic_type_water, Type.WATER)
    data object Electric : PokemonType(R.string.type_electric, 0xfff4d23c, R.drawable.ic_type_electric, Type.ELECTRIC)
    data object Grass : PokemonType(R.string.type_grass, 0xff63bc5a, R.drawable.ic_type_grass, Type.GRASS)
    data object Ice : PokemonType(R.string.type_ice, 0xff73cec0, R.drawable.ic_type_ice, Type.ICE)
    data object Fighting : PokemonType(R.string.type_fighting, 0xffce416b, R.drawable.ic_type_fighting, Type.FIGHTING)
    data object Poison : PokemonType(R.string.type_poison, 0xffb567ce, R.drawable.ic_type_poison, Type.POISON)
    data object Ground : PokemonType(R.string.type_ground, 0xffd97845, R.drawable.ic_type_ground, Type.GROUND)
    data object Flying : PokemonType(R.string.type_flying, 0xff89aae3, R.drawable.ic_type_flying, Type.FLYING)
    data object Psychic : PokemonType(R.string.type_psychic, 0xfffa7179, R.drawable.ic_type_psychic, Type.PSYCHIC)
    data object Bug : PokemonType(R.string.type_bug, 0xff91c12f, R.drawable.ic_type_bug, Type.BUG)
    data object Rock : PokemonType(R.string.type_rock, 0xffc5b78c, R.drawable.ic_type_rock, Type.ROCK)
    data object Ghost : PokemonType(R.string.type_ghost, 0xff5269ad, R.drawable.ic_type_ghost, Type.GHOST)
    data object Dragon : PokemonType(R.string.type_dragon, 0xff0b6dc3, R.drawable.ic_type_dragon, Type.DRAGON)
    data object Dark : PokemonType(R.string.type_dark, 0xff5a5465, R.drawable.ic_type_dark, Type.DARK)
    data object Steel : PokemonType(R.string.type_steel, 0xff5a8ea2, R.drawable.ic_type_steel, Type.STEEL)
    data object Fairy : PokemonType(R.string.type_fairy, 0xffec8fe6, R.drawable.ic_type_fairy, Type.FAIRY)
}
