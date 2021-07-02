package demo.lutas.gitgubusers.domain.data.entities

import com.google.gson.annotations.SerializedName

data class User (
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val name: String?,
    val bio: String?,
    val login: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    val location: String?,
    val blog: String?,
)