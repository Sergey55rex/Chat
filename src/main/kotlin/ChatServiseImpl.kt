import java.text.SimpleDateFormat
import java.util.*
class ChatServiseImpl : ChatServise {

    private val users = mutableListOf<User>()
    private val chats = mutableListOf<Chat>()

    override fun addUser(nickName: String): Long {
        val id = if (users.isEmpty()) 1 else users[users.lastIndex].id + 1
        if (users.add(User(id, nickName)))
            return id
        return -1
    }

    override fun getUsers(): List<User> = users

    override fun getChats(clientId: Long): List<Chat> = chats
            .filter {chat -> clientId == chat.firstUserId || clientId == chat.secondUserId}

    override fun getUnreadChatsCount(clientId: Long): Int  = chats.asSequence()
            .filter {chat -> clientId == chat.firstUserId || clientId == chat.secondUserId}
            .filter { chat -> chat.messages.any { message -> message.isUnread } }
            .count()

    override fun removeChat(chatId: Long): Boolean {
        chats.find { chat -> chatId == chat.id }?.apply {
            messages.clear()
            return chats.remove(this)
        }
        return false
    }

    override fun getMessage(chatId: Long, lastMessageId: Long, count: Int): List<Message> {
        return chats.find { chat -> chatId == chat.id }?.let { chat ->
            chat.messages.asSequence()
                    .filter{message -> message.id > lastMessageId}
                    .take(count)
                    .map {message -> message.apply{isUnread = false}}
                    .toList()
        } ?: emptyList()
    }

    override fun sendMessage(chatId: Long, fromUserId: Long, toUserId: Long, text: String): Boolean {
        var result = false
        val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        chats.find {chat -> chatId == chat.id}?.apply {
            val id = if (messages.isEmpty()) 1 else messages[messages.lastIndex].id + 1
            result = messages.add(Message(id, currentDate, fromUserId,toUserId, text))
        } ?: let {
            val message = Message(1, currentDate,fromUserId, toUserId, text)
            val id = if (chats.isEmpty()) 1 else chats[chats.lastIndex].id +1
            result = chats.add(Chat(id, fromUserId, toUserId, mutableListOf(message)))
        }
            return result
    }

    override fun editMessage(chatId: Long, messageId: Long, newText: String): Boolean {
        chats.find { chat -> chatId == chat.id }?.apply {
            messages.find { message -> messageId == message.id }?.apply {
            text = newText
            return true
            }
        }
        return false
    }

    override fun removeMessage(chatId: Long, messageId: Long): Boolean {
        chats.find { chat -> chatId == chat.id }?.apply {
            if (messages.size > 1) {
                messages.find {message -> messageId == message.id}?.apply{
                    return messages.remove(this)
                }
            } else if (messages.size<= 1){
                return chats.remove(this)
            }
        }
        return false
    }
}