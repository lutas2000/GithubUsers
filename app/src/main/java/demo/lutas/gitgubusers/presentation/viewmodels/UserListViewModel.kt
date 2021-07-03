package demo.lutas.gitgubusers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableLiveData<UserListState>()
    val state: LiveData<UserListState> get() = _state
    private val perPage = 20

    fun getUserList() {
        viewModelScope.launch {
            userRepository.getUsers(0, perPage)
                .onStart { _state.value = UserListState.Loading }
                .catch { e -> _state.value = UserListState.Fail(e) }
                .collect { value -> _state.value = UserListState.Success(value) }
        }
    }
}