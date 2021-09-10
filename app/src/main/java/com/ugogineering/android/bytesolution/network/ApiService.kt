package com.ugogineering.android.bytesolution.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ugogineering.android.bytesolution.BuildConfig
import com.ugogineering.android.bytesolution.model.*
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.*

private const val BASE_URL = "http://34.65.124.52/byte/"
private const val BASE_URL2 = "https://api.printful.com/"

// Creating Moshi instance
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Creating a Retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder().also { client ->
            if(BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
    ).build()

// Creating a second Retrofit object to fetch countries
private val retrofit2 = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL2)
    .client(
        OkHttpClient.Builder().also { client ->
            if(BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
    ).build()

interface ApiService {
    @FormUrlEncoded
    @POST("admin_signup.php")
    fun adminSignup(
        @Field("firstname" ) firstname: String,
        @Field("lastname" ) lastname: String,
        @Field("gender" ) gender: String,
        @Field("dob" ) dob: String,
        @Field("address" ) address: String,
        @Field("country" ) country: String,
        @Field("username" ) username: String,
        @Field("password" ) password: String,
        @Field("image" ) image: String
    ):
            Deferred<AdminSignupResponse>

    @FormUrlEncoded
    @POST("admin_login.php")
    fun adminSignin(adminSigninBody: AdminSigninBody):
            Deferred<AdminSigninResponse>

    @GET("get_employee.php")
    fun getEmployees():
            Deferred<EmployeeList>

    @GET("countries")
    fun getCountries():
            Deferred<CountriesList>


}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

object Api2 {
    val retrofitService2: ApiService by lazy {
        retrofit2.create(ApiService::class.java)
    }
}