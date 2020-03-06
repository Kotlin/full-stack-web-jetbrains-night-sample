package services

import JSON_PLACEHOLDER_URL
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import model.User
import org.w3c.fetch.RequestInit
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlin.js.json

class UserService(private val coroutineContext: CoroutineContext) {
    suspend fun getUsers(): List<User> {
        val response = fetch("$JSON_PLACEHOLDER_URL/users")
        return Json.parse(User.serializer().list, response)
    }

    suspend fun getUser(id: Int): User {
        val response = fetch("$JSON_PLACEHOLDER_URL/users/$id")
        return Json.parse(User.serializer(), response)
    }

    private suspend fun fetch(url: String): String {
        return withContext(coroutineContext) {
            val response = window.fetch(url, RequestInit("GET", headers = json(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            ))).await()

            response.text().await()
        }
    }
}