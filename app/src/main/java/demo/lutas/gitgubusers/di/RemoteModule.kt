package demo.lutas.gitgubusers.di

import demo.lutas.gitgubusers.presentation.data.remote.RestClient
import org.koin.dsl.module

val remoteModule = module {
    single { RestClient() }
}

private inline fun <reified T> createService(client: RestClient): T {
    return client.createService(T::class.java)
}