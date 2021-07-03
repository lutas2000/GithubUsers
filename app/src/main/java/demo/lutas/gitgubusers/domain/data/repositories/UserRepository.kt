package demo.lutas.gitgubusers.domain.data.repositories

import demo.lutas.gitgubusers.domain.data.entities.User

interface UserRepository {
    suspend fun getUsers(accept: String, since: Int, per_page: Int): List<User>
    suspend fun getUserDetail(): User
}