package br.com.messore.tech.pokedex.data.network.builder

class PokemonQueryBuilder {
    private val map = mutableMapOf(
        "offset" to 0,
        "where" to mutableListOf(
            "name: {_ilike: \$term}"
        )
    )

    fun limit(limit: Int) = apply {
        map["limit"] = limit
    }

    fun offset(offset: Int) = apply {
        map["offset"] = offset
    }

    @Suppress("UNCHECKED_CAST")
    fun where(where: String?) = apply {
        if (where == null) return@apply

        val whereList = map["where"] as MutableList<String>
        whereList.add(where)
    }

    fun sort(sort: String?) = apply {
        map["order_by"] = "{${sort ?: ""}}"
    }

    fun build(): String {
        val clause = map.entries
            .map { (key, value) ->
                when (value) {
                    is List<*> -> value.joinToString(prefix = "where: {", postfix = "}") { "$it" }
                    else -> "${key}: $value"
                }
            }
            .joinToString(", ")

        return """
            query (${'$'}term: String = "%%") {
              pokemon: pokemon_v2_pokemon($clause) {
                id
                name
                types: pokemon_v2_pokemontypes(order_by: {slot: asc}) {
                  type: pokemon_v2_type {
                    id
                    name
                  }
                }
              }
            }
        """.trimIndent()
    }
}
