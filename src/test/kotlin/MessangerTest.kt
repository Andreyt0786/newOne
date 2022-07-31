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
        assertEquals(1, result)

    }

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
        service.createChart(chat2)
        val element: List<Chats> = listOf(chat, chat2)
        val result = service.getChat()
        assertEquals(result, element)
    }

    @Test
    fun createChartTrue() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        val newChat = service.createChart(chat)
        assertEquals(chat, newChat)
    }

    @Test(expected = PostNotFoundException::class)
    fun createChartFalse() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        service.createChart(chat)

    }

    @Test
    fun createMessagesTrue() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        val result = service.createMessage(chat, message4)
        assertEquals(result, message4)
    }

    @Test(expected = PostNotFoundException::class)
    fun createMessagesFalse() {
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        val result = service.createMessage(chat2, message4)
    }

    @Test (expected = PostNotFoundException::class)
    fun findMessagesFalse(){
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        service.findMessages(chat2)
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
        val chatMessages = chat.messages.filter { it.unread == false && it.import == true }
        val result = service.findMessages(chat)
        assertEquals(chatMessages,result)

    }

    @Test
    fun delete(){
        val service = Messanger()
        val message1 = Messages(1, true, false, "first", false)
        val message2 = Messages(2, false, true, "sec", false)
        val message3 = Messages(3, false, true, "third", false)
        val message4 = Messages(4, false, true, "fourth", false)

        val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
        val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
        val chat3 = Chats(3, 3, messages = mutableListOf())
        service.createChart(chat)
        service.createChart(chat2)
        service.createChart(chat3)
        service.delete(chat2)
        val result = service.element
        val etalon = listOf(chat, chat3)
        assertEquals(etalon, result)
    }
}
