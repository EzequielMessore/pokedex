package br.com.messore.tech.pokedex.network.di

import br.com.messore.tech.pokedex.network.extensions.addInterceptors
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ServiceModules::class])
class NetworkModule {
    private val url = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(factory)
            .client(client)
            .baseUrl(url)
            .build()
    }

    @Provides
    @Singleton
    fun providesConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttp(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptors(interceptors)
            .build()
    }

    @IntoSet
    @Provides
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
