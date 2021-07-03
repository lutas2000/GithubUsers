package demo.lutas.gitgubusers

import android.app.Application
import demo.lutas.gitgubusers.di.remoteModule
import org.koin.core.context.GlobalContext.startKoin

class GithubApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(remoteModule)
        }
    }
}