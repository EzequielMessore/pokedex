package br.com.messore.tech.pokedex.data.data.di

import br.com.messore.tech.pokedex.data.data.repository.PokemonRepositoryImpl
import br.com.messore.tech.pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun providesPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository
}
