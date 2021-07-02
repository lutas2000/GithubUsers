package demo.lutas.gitgubusers.presentation.data.remote

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    class HeaderInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original.newBuilder()
                .header("Accept", "application/vnd.github.v3+json")
                .method(original.method(), original.body())
                .build()

            return chain.proceed(request);
        }
    }

    private val retrofit: Retrofit = createRetrofit()

    private fun createRetrofit(): Retrofit {
        val client = createOkhttpClient()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    private fun createOkhttpClient(): OkHttpClient {
        val headerInterceptor = HeaderInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun <T> createService(_class: Class<T>): T {
        return retrofit.create(_class)
    }
}