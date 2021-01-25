data class Chat (
    val id: Long,
    val firstUserId: Long,
    val secondUserId: Long,
    val messages: MutableList<Message>,
)
