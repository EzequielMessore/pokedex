package br.com.messore.tech.pokedex.domain.usecase

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class ListPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(): List<Pokemon> {
        return repository.getPokemons()
    }
}
