package com.example.testingapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.database.EventDatabase
import com.example.testingapplication.repository.ApiService
import com.example.testingapplication.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideNetworkUtil(@ApplicationContext context: Context): NetworkUtil {
        return NetworkUtil(context)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com/discovery/v2/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
//        retryInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(headerInterceptor)
//        okHttpBuilder.addInterceptor(retryInterceptor)
//        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }


    @Provides
    fun headerInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()

        val url: HttpUrl = original.url.newBuilder()
            .build()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .url(url)
            .build()

        chain.proceed(request)
    }

//    @Provides
//    fun provideRetryInterceptor(): Interceptor = Interceptor { chain ->
//        val request = chain.request()
//        var response = chain.proceed(request)
//        var retryCount = 0
//        val maxRetries = 2
//
//        while (!response.isSuccessful && retryCount < maxRetries) {
//            retryCount++
//            response.close()
//            Thread.sleep(500L * retryCount) // Exponential backoff: 1s, 2s
//            response = chain.proceed(request)
//        }
//        response
//    }


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java,
            EventDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideEventDao(database: EventDatabase) = database.eventDao()

}

const val timeoutRead = 600   //In seconds
const val contentType = "Content-Type"
const val contentTypeValue = "application/json"
const val timeoutConnect = 600   //In seconds