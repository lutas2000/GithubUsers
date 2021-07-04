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

class UserDetailViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableLiveData<UserDetailState>()
    val state: LiveData<UserDetailState> get() = _state

    fun getUserDetail(login: String) {
        viewModelScope.launch {
            userRepository.getUserDetail(login)
                .onStart { _state.value = UserDetailState.Loading }
                .catch { e -> _state.value = UserDetailState.Fail(e) }
                .collect { value -> _state.value = UserDetailState.Success(value) }
        }
    }
}