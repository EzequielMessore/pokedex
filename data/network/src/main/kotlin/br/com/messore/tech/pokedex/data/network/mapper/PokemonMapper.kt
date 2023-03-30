package br.com.messore.tech.pokedex.data.network.mapper

import br.com.messore.tech.pokedex.data.network.service.TestResponse
import br.com.messore.tech.pokedex.data.network.service.TypeElement
import br.com.messore.tech.pokedex.domain.model.Pokemon as PokemonDomain
import br.com.messore.tech.pokedex.domain.model.Type as TypeDomain

fun TestResponse.toDomain(): List<PokemonDomain> {
    return this.data.pokemon.map { pokemon ->
        PokemonDomain(
            id = pokemon.id,
            name = pokemon.name,
            types = pokemon.types.map(TypeElement::toDomain),
        )
    }
}

fun TypeElement.toDomain(): TypeDomain {
    return TypeDomain(id = type.id, name = type.name)
}
