package br.com.messore.tech.pokedex.pokelist.mapper

import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.pokelist.R
import br.com.messore.tech.pokedex.pokelist.model.Pokemon
import br.com.messore.tech.pokedex.pokelist.model.PokemonType
import br.com.messore.tech.pokedex.domain.model.Pokemon as PokemonDomain
import br.com.messore.tech.pokedex.domain.model.PokemonType as PokemonTypeDomain

fun List<PokemonDomain>.toModel() = map(PokemonDomain::toModel)

fun PokemonDomain.toModel(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        image = image,
        types = types.map(PokemonTypeDomain::toModel),
    )
}

fun PokemonTypeDomain.toModel(): PokemonType {
    return when (this) {
        PokemonTypeDomain.NORMAL -> PokemonType.Normal
        PokemonTypeDomain.FIRE -> PokemonType.Fire
        PokemonTypeDomain.WATER -> PokemonType.Water
        PokemonTypeDomain.ELECTRIC -> PokemonType.Electric
        PokemonTypeDomain.GRASS -> PokemonType.Grass
        PokemonTypeDomain.ICE -> PokemonType.Ice
        PokemonTypeDomain.FIGHTING -> PokemonType.Fighting
        PokemonTypeDomain.POISON -> PokemonType.Poison
        PokemonTypeDomain.GROUND -> PokemonType.Ground
        PokemonTypeDomain.FLYING -> PokemonType.Flying
        PokemonTypeDomain.PSYCHIC -> PokemonType.Psychic
        PokemonTypeDomain.BUG -> PokemonType.Bug
        PokemonTypeDomain.ROCK -> PokemonType.Rock
        PokemonTypeDomain.GHOST -> PokemonType.Ghost
        PokemonTypeDomain.DRAGON -> PokemonType.Dragon
        PokemonTypeDomain.DARK -> PokemonType.Dark
        PokemonTypeDomain.STEEL -> PokemonType.Steel
        PokemonTypeDomain.FAIRY -> PokemonType.Fairy
    }
}

fun PokemonSort.toModel(): Int = when (this) {
    PokemonSort.NAME_ASC -> R.string.lower_name
    PokemonSort.NAME_DESC -> R.string.higher_name
    PokemonSort.NUMBER_ASC -> R.string.lower_number
    PokemonSort.NUMBER_DESC -> R.string.higher_number
}
