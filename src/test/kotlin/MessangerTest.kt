import org.junit.Test

import org.junit.Assert.*

class MessangerTest {

    @Test
    fun getUnreadMessageCount() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        val result = service.getUnreadMessageCount(false)
        val etalon = " Количество непрочитанных сообщений равно : 1, в 1 чатах"
        assertEquals(etalon, result)

    }
/*
    @Test
    fun getChat() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        val result = service.getChat()
        assertEquals(result, chat)
    }

    @Test
    fun findMessagesTrue() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        service.findMessages(chat)
        chat2.run {
            println("Чат id= ${id}")
            println("Колличество сообщений ${messages.count()}")
            println("Последнее сообщение - ${messages.last().id}")
            println("Последнее сообщение - ${messages.last()}")
        }
        assertEquals(service.findMessages(chat), chat2)
    }

    @Test
    fun findMessagesFalse() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        val result = service.findMessages(chat2)
        val etalon = "Чата с таким id ${chat2.id} нет"

        assertEquals(etalon, print(result))
    }

    @Test
    fun  createChart(){
    val service = Messanger()
    val message1 = Messages(1, true, false, "first", false)
    val message2 = Messages(2, false, true, "sec", false)
    val message3 = Messages(3, false, true, "third", false)
    val message4 = Messages(4, false, true, "fourth", false)

    val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
    val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
    service.createChart(chat)
    service.createChart(chat)
    val result = service.createChart(chat)
    val etalon = "Чат с этим пользователем есть"

    assertEquals(etalon, result)
    }
    */
}

