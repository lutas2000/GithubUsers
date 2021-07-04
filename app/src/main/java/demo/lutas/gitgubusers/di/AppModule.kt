package demo.lutas.gitgubusers.di

import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.repositories.UserRemoteRepository
import demo.lutas.gitgubusers.presentation.viewmodels.UserDetailViewModel
import demo.lutas.gitgubusers.presentation.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repository
    single<UserRepository> { UserRemoteRepository(get()) }

    // ViewModel
    viewModel { UserListViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
}