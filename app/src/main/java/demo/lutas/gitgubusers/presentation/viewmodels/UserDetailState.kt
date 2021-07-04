package demo.lutas.gitgubusers.presentation.viewmodels

import demo.lutas.gitgubusers.domain.data.entities.User

sealed class UserDetailState {
    object Loading: UserDetailState()
    class Success(val user: User): UserDetailState()
    class Fail(val error: Throwable): UserDetailState()
}
