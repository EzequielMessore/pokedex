package br.com.messore.tech.pokedex.data.network.di

import br.com.messore.tech.pokedex.data.network.service.PokemonService
import br.com.messore.tech.pokedex.data.network.service.TypeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ServiceModules {

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create()

    @Provides
    fun provideTypeService(retrofit: Retrofit): TypeService = retrofit.create()
}
