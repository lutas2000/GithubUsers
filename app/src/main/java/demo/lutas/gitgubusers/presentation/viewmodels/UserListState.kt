package demo.lutas.gitgubusers.presentation.viewmodels

import demo.lutas.gitgubusers.domain.data.entities.User

sealed class UserListState {
    object Loading: UserListState()
    class Success(val userList: List<User>): UserListState()
    class Fail(val error: Throwable): UserListState()
}
