package br.com.messore.tech.pokedex.data.network.mapper

import br.com.messore.tech.pokedex.data.network.model.PokemonResponse
import br.com.messore.tech.pokedex.data.network.model.TypeElement
import br.com.messore.tech.pokedex.domain.extension.capitalize
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.model.Pokemon as PokemonDomain
import br.com.messore.tech.pokedex.domain.model.PokemonType as TypeDomain

fun PokemonResponse.toDomain(): List<PokemonDomain> = data.pokemon.map { pokemon ->
    PokemonDomain(
        id = pokemon.id,
        name = pokemon.name.capitalize(),
        types = pokemon.types.map(TypeElement::toDomain),
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
    )
}

fun TypeElement.toDomain(): TypeDomain = TypeDomain.byId(type.id)

fun PokemonSort.toRemote() = when (this) {
    PokemonSort.NAME_ASC -> "name: asc"
    PokemonSort.NAME_DESC -> "name: desc"
    PokemonSort.NUMBER_ASC -> "id: asc"
    PokemonSort.NUMBER_DESC -> "id: desc"
}
