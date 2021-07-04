package demo.lutas.gitgubusers.presentation.data.remote

import demo.lutas.gitgubusers.domain.data.entities.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): List<User>

    @GET("/users/{userName}")
    suspend fun getUserDetail(
        @Path("userName") userName: String
    ): User
}