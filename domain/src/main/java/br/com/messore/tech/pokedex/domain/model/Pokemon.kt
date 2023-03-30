package br.com.messore.tech.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Type>
)
