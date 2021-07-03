package demo.lutas.gitgubusers.domain.data.repositories

import demo.lutas.gitgubusers.domain.data.entities.User

interface UserRepository {
    suspend fun getUsers(since: Int, per_page: Int): List<User>
    suspend fun getUserDetail(): User
}