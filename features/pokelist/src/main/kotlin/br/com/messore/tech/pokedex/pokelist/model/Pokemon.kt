package br.com.messore.tech.pokedex.pokelist.model

typealias Url = String

data class Pokemon(
    val id: Int,
    val name: String,
    val image: Url,
    val types: List<PokemonType>,
) {
    val mainType = types.first()
}
