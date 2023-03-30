package br.com.messore.tech.pokedex.data.network.di

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.data.network.source.PokemonRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
interface RemoteModule {
    @Binds
    fun providesPokemonDataSource(impl: PokemonRemoteDataSource): PokemonDataSource.Remote
}
