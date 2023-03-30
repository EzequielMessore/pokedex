package br.com.messore.tech.pokedex.di

import br.com.messore.tech.pokedex.data.data.di.DataModule
import br.com.messore.tech.pokedex.data.network.di.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DataModule::class,
        NetworkModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class AppModule
