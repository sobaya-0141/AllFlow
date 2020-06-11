package sobaya.example.allflow.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sobaya.example.allflow.repository.GithubRepository
import sobaya.example.allflow.repository.GithubService

val networkModule = module {
    single { createMoshi() }
    single { createOkHttp() }
    single { createRetrofit(get(), get()) }
    single<GithubService> { createApi(get())}
    single { GithubRepository(get()) }
}

fun createMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


fun createOkHttp(): OkHttpClient {

    val builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()

    logging.level = if (BuildConfig.BUILD_TYPE == "debug") HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    builder.addInterceptor(logging)

    return builder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://api.github.com")
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .build()

fun createApi(retrofit: Retrofit) = retrofit.create(GithubService::class.java)