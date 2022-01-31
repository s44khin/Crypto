package ru.s44khin.crypto.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.s44khin.crypto.data.network.API_KEY
import ru.s44khin.crypto.data.network.CoinRepository
import ru.s44khin.crypto.data.network.CoinRepositoryImpl
import ru.s44khin.crypto.data.network.CoinService

internal const val SERVER = "https://api.coincap.io/v2/"

@Module
object NetworkModule {

    @Provides
    fun provideHttpNetworkInterceptor() = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .build()

        chain.proceed(request)
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideCoinService(
        httpNetworkInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        moshiConverterFactory: MoshiConverterFactory
    ): CoinService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpNetworkInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)

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