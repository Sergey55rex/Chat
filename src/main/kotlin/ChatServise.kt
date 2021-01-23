interface ChatServise {
    fun addUser(nicName: String): Long
    fun getUsers(): List<User>

    fun getChats(clientId:Long): List<Chat>
    fun getUnreadChatsCount(clientId: Long): Int
    fun removeChat(chatId: Long): Boolean

    fun getMessage(chatId: Long, listMessageId: Long, count: Int): List<Message>
    fun sendMessage(chatId: Long, fromUserId: Long, toUserId: Long, text: String): Boolean
    fun editMessage(chatId: Long, messageId: Long, newText: String): Boolean
    fun removeMessage(chatId: Long, messageId: Long): Boolean
}