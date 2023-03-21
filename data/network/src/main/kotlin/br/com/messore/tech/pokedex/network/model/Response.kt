package br.com.messore.tech.pokedex.network.model

data class Response<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
