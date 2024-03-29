package br.com.messore.tech.pokedex.domain.model

typealias Url = String

data class Pokemon(
    val id: Int,
    val image: Url,
    val name: String,
    val types: List<PokemonType>,
)
