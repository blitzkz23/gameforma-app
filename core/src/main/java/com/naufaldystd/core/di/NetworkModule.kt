package com.naufaldystd.core.di

import com.naufaldystd.core.data.source.remote.network.ApiService
import com.naufaldystd.core.utils.Constants.BASE_URL
import com.naufaldystd.core.utils.Constants.HOSTNAME
import com.naufaldystd.core.utils.Constants.PIN_A
import com.naufaldystd.core.utils.Constants.PIN_B
import com.naufaldystd.core.utils.Constants.PIN_C
import com.naufaldystd.core.utils.Constants.PIN_D
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Provides
	fun provideOkHttpClient(): OkHttpClient {
		val certificatePinner = CertificatePinner.Builder()
			.add(
				HOSTNAME,
				PIN_A,
				PIN_B,
				PIN_C,
				PIN_D
			)
			.build()

		return OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.connectTimeout(120, TimeUnit.SECONDS)
			.readTimeout(120, TimeUnit.SECONDS)
			.certificatePinner(certificatePinner)
			.build()
	}

	@Provides
	fun provideApiService(client: OkHttpClient): ApiService {
		val retrofit = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
		return retrofit.create(ApiService::class.java)
	}
}