package demo.lutas.gitgubusers.domain.data.entities

import com.google.gson.annotations.SerializedName

data class User (
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val name: String? = null,
    val bio: String? = null,
    val login: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    val location: String? = null,
    val blog: String? = null,
)