package ru.skillbranch.skillarticles.repository.models

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("accessToken") val accessToken: String
)