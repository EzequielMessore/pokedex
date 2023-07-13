package br.com.messore.tech.pokedex.data.network.model

data class GraphQLRequest(
    val query: String,
    val variables: Map<String, Any?>,
)
