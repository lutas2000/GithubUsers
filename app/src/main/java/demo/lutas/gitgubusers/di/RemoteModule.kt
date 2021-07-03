package demo.lutas.gitgubusers.di

import demo.lutas.gitgubusers.presentation.data.remote.RestClient
import demo.lutas.gitgubusers.presentation.data.remote.UserService
import org.koin.dsl.module

val remoteModule = module {
    single { RestClient() }
    single { createService<UserService>(get()) }
}

private inline fun <reified T> createService(client: RestClient): T {
    return client.createService(T::class.java)
}