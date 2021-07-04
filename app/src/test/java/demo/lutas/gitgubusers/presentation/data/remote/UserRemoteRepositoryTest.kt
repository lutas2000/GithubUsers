package demo.lutas.gitgubusers.presentation.data.remote

import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.presentation.data.repositories.UserRemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserRemoteRepositoryTest {
    @MockK
    lateinit var service: UserService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `get user detail Success`() = runBlocking {
        // arrange
        val repository = UserRemoteRepository(service)
        val user = User(
            id = 1,
            login = "User 1",
            avatarUrl = "https://google.com",
            siteAdmin = true
        )
        coEvery { service.getUserDetail(any()) } returns user
        // action
        val result = repository.getUserDetail("test").first()
        // assert
        assertEquals(user, result)
        coVerify { service.getUserDetail("test") }
    }

    @Test
    fun `get user detail Failed`() = runBlocking {
        // arrange
        val repository = UserRemoteRepository(service)
        coEvery { service.getUserDetail(any()) } throws IllegalAccessException()
        // action
        var result: Throwable? = null
        try {
            repository.getUserDetail("test").first()
        } catch (e: Throwable) {
            result = e
        }
        // assert
        assertTrue(result is IllegalAccessException)
        coVerify { service.getUserDetail("test") }
    }
}