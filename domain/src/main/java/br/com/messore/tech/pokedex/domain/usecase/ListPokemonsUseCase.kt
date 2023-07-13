package br.com.messore.tech.pokedex.domain.usecase

import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.model.PokemonType
import br.com.messore.tech.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class ListPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(
        page: Int,
        term: String,
        pageSize: Int,
        type: PokemonType? = null,
        sort: PokemonSort? = null,
    ) = repository.getPokemons(page, term.ifEmpty { null }, pageSize, type, sort)
}
