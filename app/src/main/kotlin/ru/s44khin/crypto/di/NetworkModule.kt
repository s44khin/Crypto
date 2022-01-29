package ru.s44khin.crypto.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.s44khin.crypto.data.network.API_KEY
import ru.s44khin.crypto.data.network.CoinRepository
import ru.s44khin.crypto.data.network.CoinRepositoryImpl
import ru.s44khin.crypto.data.network.CoinService

internal const val SERVER = "https://pro-api.coinmarketcap.com/"

@Module
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideCoinService(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        moshiConverterFactory: MoshiConverterFactory
    ): CoinService {
        val okHttpClient = OkHttpClient.Builder().apply {
            authenticator { _, response ->
                response.request.newBuilder()
                    .header("X-CMC_PRO_API_KEY", API_KEY)
                    .build()
            }

            addInterceptor(httpLoggingInterceptor)
        }

        val retrofitClient = Retrofit.Builder().apply {
            baseUrl(SERVER)
            client(okHttpClient.build())
            addConverterFactory(moshiConverterFactory)
        }

        return retrofitClient.build()
            .create(CoinService::class.java)
    }

    @Provides
    fun provideCoinRepository(coinService: CoinService): CoinRepository {
        return CoinRepositoryImpl(coinService)
    }
}