package br.com.messore.tech.pokedex.data.network.model

data class PokemonResponse(
    val data: Data,
) {
    data class Data(
        val pokemon: List<Pokemon>,
    )
}

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<TypeElement>,
)

data class TypeElement(
    val type: PokemonType,
)

data class PokemonType(
    val id: Int,
    val name: String,
)
