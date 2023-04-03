package br.com.messore.tech.pokedex.data.data.repository

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonDataSource.Remote
) : PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        return remoteDataSource.getPokemons()
    }
}
