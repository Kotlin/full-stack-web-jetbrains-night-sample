package database

import org.jetbrains.exposed.dao.IntIdTable

object Posts : IntIdTable() {
    val postId = integer("postId")
    val userId = integer("userId")
    val title = varchar("title", 50)
    val body = varchar("body", 255)
}