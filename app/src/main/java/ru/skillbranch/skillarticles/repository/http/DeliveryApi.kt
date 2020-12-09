package ru.skillbranch.skillarticles.repository.http

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import ru.skillbranch.skillarticles.repository.models.Dish
import ru.skillbranch.skillarticles.repository.models.RefreshToken
import ru.skillbranch.skillarticles.repository.models.Token

interface DeliveryApi {

    @POST("auth/refresh")
    fun refreshToken(@Body refreshToken: RefreshToken): Single<Token>

    @GET("dishes")
    @Headers("If-Modified-Since: Mon, 1 Jun 2020 08:00:00 GMT")
    fun getDishes(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") token: String
    ): Single<List<Dish>>
}