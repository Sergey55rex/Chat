fun main(){
    val servise: ChatServise = ChatServiseImpl()

    val user1 = servise.addUser("Sergey")
    val user2 = servise.addUser("Alex")

    println(servise.getUsers())

    servise.sendMessage(1,user1,user2,"Привет")
    servise.sendMessage(1,user1,user2,"Здорово")
    println(servise.getChats(user1))
    println(servise.getChats(user2))
    val chat1 =servise.getChats(user1)[0]
println()
    servise.sendMessage(1,user1,user2,"Пока")
    println(servise.getMessage(chatId = chat1.id, listMessageId = 1, count = 3))
    println()
    println(servise.getChats(user1))
    println(servise.getUnreadChatsCount(2))
    println(servise.getChats(1))
    println(servise.removeMessage(1,2))
    println(servise.getMessage(1,1,3))

    println(servise.removeChat(1))
}