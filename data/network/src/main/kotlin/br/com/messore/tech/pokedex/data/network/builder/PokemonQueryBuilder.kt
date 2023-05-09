package br.com.messore.tech.pokedex.data.network.builder

class PokemonQueryBuilder(
    private val operationName: String,
) {
    private val map = mutableMapOf<String, Any>("offset" to 0)

    fun limit(limit: Int) = apply {
        map["limit"] = limit
    }

    fun offset(offset: Int) = apply {
        map["offset"] = offset
    }

    fun where(where: String?) = apply {
        map["where"] = "{${where ?: ""}}"
    }

    fun sort(sort: String?) = apply {
        map["order_by"] = "{${sort ?: ""}}"
    }

    fun build(): String {
        val clause = map.entries.joinToString(", ") { "${it.key}: ${it.value}" }

        return """
            query $operationName {pokemon: pokemon_v2_pokemon($clause)
            {id,name,types: pokemon_v2_pokemontypes(order_by: {slot: asc}) {type: pokemon_v2_type {id,name}}}}
        """.trimIndent()
    }
}
