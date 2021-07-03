package demo.lutas.gitgubusers.domain.data.repositories

import demo.lutas.gitgubusers.domain.data.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(since: Int, perPage: Int): Flow<List<User>>
    fun getUserDetail(): Flow<User>
}