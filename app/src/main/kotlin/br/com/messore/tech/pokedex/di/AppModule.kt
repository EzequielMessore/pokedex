package br.com.messore.tech.pokedex.di

import br.com.messore.tech.pokedex.network.di.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class AppModule
