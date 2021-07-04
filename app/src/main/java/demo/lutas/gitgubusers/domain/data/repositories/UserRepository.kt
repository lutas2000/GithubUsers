package demo.lutas.gitgubusers.domain.data.repositories

import androidx.paging.PagingData
import demo.lutas.gitgubusers.domain.data.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(perPage: Int): Flow<PagingData<User>>
    fun getUserDetail(login: String): Flow<User>
}