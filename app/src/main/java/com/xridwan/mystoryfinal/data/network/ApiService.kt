package com.xridwan.mystoryfinal.data.network

import com.xridwan.mystoryfinal.data.network.response.FileUploadResponse
import com.xridwan.mystoryfinal.data.network.response.LoginResponse
import com.xridwan.mystoryfinal.data.network.response.RegisterResponse
import com.xridwan.mystoryfinal.data.network.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") token: String
    ): StoryResponse

    @Multipart
    @POST("stories")
    suspend fun postImage(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Double?,
        @Part("lon") lon: Double?,
        @Header("Authorization") token: String,
        @Header("Accept") type: String,
    ): FileUploadResponse

    @GET("stories")
    suspend fun getStoriesWithLocation(
        @Query("location") loc: Int,
        @Header("Authorization") token: String
    ): StoryResponse
}