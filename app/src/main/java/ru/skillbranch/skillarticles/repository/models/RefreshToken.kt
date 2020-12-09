package ru.skillbranch.skillarticles.repository.models

import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("refreshToken") val refreshToken: String
)