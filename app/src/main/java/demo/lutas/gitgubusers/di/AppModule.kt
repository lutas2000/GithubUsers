package demo.lutas.gitgubusers.di

import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.repositories.UserRemoteRepository
import org.koin.dsl.module

val appModule = module {
    // Repository
    single<UserRepository> { UserRemoteRepository(get()) }
}