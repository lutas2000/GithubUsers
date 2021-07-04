package demo.lutas.gitgubusers.presentation.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.datasources.UserListPagingSource
import demo.lutas.gitgubusers.presentation.data.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val service: UserService
): UserRepository {
    override fun getUsers(perPage: Int): Flow<PagingData<User>> {
        return Pager(
            PagingConfig(pageSize = perPage)
        ) {
            UserListPagingSource(service, perPage)
        }.flow
    }

    override fun getUserDetail(login: String): Flow<User> {
        return flow {
            val user = service.getUserDetail(login)
            emit(user)
        }.flowOn(Dispatchers.IO)
    }
}