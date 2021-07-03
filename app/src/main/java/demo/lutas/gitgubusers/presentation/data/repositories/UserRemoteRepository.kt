package demo.lutas.gitgubusers.presentation.data.repositories

import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.remote.UserService

class UserRemoteRepository(private val service: UserService): UserRepository {
    override suspend fun getUsers(since: Int, perPage: Int): List<User> {
        return service.getUsers(since, perPage)
    }

    override suspend fun getUserDetail(): User {
        TODO("Not yet implemented")
    }
}