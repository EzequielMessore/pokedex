package br.com.messore.tech.pokedex.domain.usecase

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonType
import br.com.messore.tech.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class ListPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(page: Int, pageSize: Int, type: PokemonType? = null): List<Pokemon> {
        return repository.getPokemons(page, pageSize, type)
    }
}
