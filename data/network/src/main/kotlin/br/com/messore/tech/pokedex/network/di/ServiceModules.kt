package br.com.messore.tech.pokedex.network.di

import br.com.messore.tech.pokedex.network.service.TypeService
import br.com.messore.tech.pokedex.network.service.UrlService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ServiceModules {

    @Provides
    fun provideUrlService(retrofit: Retrofit): UrlService = retrofit.create()

    @Provides
    fun provideTypeService(retrofit: Retrofit): TypeService = retrofit.create()
}
