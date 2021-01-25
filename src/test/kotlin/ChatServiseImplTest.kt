import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class ChatServiseImplTest {
    val servise: ChatServise = ChatServiseImpl()
    @Test
    fun addUser() {
        val exception = User(1, "Sergey")
        val result = servise.addUser(exception.nickName)
        assertEquals(1,result)
    }

    @Test
    fun getUsers() {
        servise.addUser("Sergey")
        val exception = User(1, "Sergey")
        val result = servise.getUsers()[0]
        assertEquals(exception,result)
    }

    @Test
    fun getChats() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        val exception = Chat(1,1,2, servise.getChats(1)[0].messages)
        val result = servise.getChats(1)[0]
        assertEquals(exception,result)
    }

    @Test
    fun getUnreadChatsCount() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        Chat(1,1,2, servise.getChats(1)[0].messages)
        val exception = if (servise.getChats(1)[0].messages[0].isUnread == true) {1
        } else {-1}
        val result = servise.getUnreadChatsCount(1)
        assertEquals(exception, result)
    }

    @Test
    fun removeChat() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        Chat(1,1,2, servise.getChats(1)[0].messages)
        val exception: Long = 1
        val result = servise.removeChat(exception)
        assertTrue(result)
    }

    @Test
    fun getMessage() {
        val user1 = servise.addUser("Sergey")
        val user2 = servise.addUser("Alex")
        servise.sendMessage(1,user1,user2,"Привет")
        servise.sendMessage(1,user1,user2,"Здорово")
        val exception = Message(2, SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),1,2,"Здорово", false)
        val result = servise.getMessage(1,1,1)[0]
        assertEquals(exception, result)
    }

    @Test
    fun sendMessage() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        val result = servise.sendMessage(1,1,1,"Привет")
        assertTrue(result)
    }

    @Test
    fun editMessage() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        val result = servise.editMessage(1,1,"Пока!")
        assertTrue(result)
    }

    @Test
    fun removeMessage() {
        val client1 = servise.addUser("Sergey")
        val client2 = servise.addUser("Alex")
        servise.sendMessage(1,client1,client2,"Привет")
        val result = servise.removeMessage(1,1)
        assertTrue(result)
    }

}