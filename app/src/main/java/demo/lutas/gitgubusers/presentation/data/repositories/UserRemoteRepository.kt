package demo.lutas.gitgubusers.presentation.data.repositories

import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRemoteRepository(private val service: UserService): UserRepository {
    override fun getUsers(since: Int, perPage: Int): Flow<List<User>> {
        return flow {
            val list = service.getUsers(since, perPage)
            emit(list)
        }.flowOn(Dispatchers.IO)
    }

    override fun getUserDetail(login: String): Flow<User> {
        return flow {
            val user = service.getUserDetail(login)
            emit(user)
        }.flowOn(Dispatchers.IO)
    }
}