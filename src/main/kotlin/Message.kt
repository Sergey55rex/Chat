data class Message(
    val id: Long,
    val date: String,
    val fromUserId: Long,
    val toUserId: Long,
    var text: String,
    var isUnread: Boolean = true

)