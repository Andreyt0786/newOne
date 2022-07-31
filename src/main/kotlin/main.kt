open class Item(val id: Int)

class Chats(
    id: Int,
    val idPerson: Int,
    val messages: MutableList<Messages> = mutableListOf(),
) : Item(id) {

    override fun toString(): String {
        return "Id client $idPerson and messages $messages"
    }
}


class Messages(
    id: Int,
    val import: Boolean,
    val export: Boolean,
    val text: String,
    var unread: Boolean,
) : Item(id) {
    override fun toString(): String {
        return text
    }
}


abstract class ChatService<T : Item> {

    var element = mutableListOf<T>()


    fun delete(elem: T) {
        element.remove(elem)
    }

    fun print() {
        print(element)
    }

    fun count() {
        element.size
    }

    fun clear() {
        element.clear()
    }

}

class Messanger : ChatService<Chats>() {

    fun getUnreadMessageCount(read: Boolean = false): Int {
        val countMessages = element.flatMap {
            it.messages
        }
            .count { it.unread == read && it.import == true }

        val countChat = element.count { chat ->
            chat.messages.any() { it.unread == read }
        }

        return countMessages
    }


    fun getChat(): List<Chats> {
        val elementList = element.filter {
            it.messages.size > 0
        }
        return elementList
    }

    fun findMessages(chat: Chats): List<Messages> {

        val elementList = element.find { it.id == chat.id }
            ?.run {
                element.flatMap {
                    it.messages
                }
                    .filter { it.unread == false && it.import == true }
            }
            ?: throw PostNotFoundException("Чата с id ${chat.id} нет")
          element.find { it.id == chat.id }?.messages?.filter{!it.unread }?.map { it.unread = true }
        return elementList
    }

    fun createMessage(chat: Chats, message: Messages): Messages? {

        element.find { it.id == chat.id && it.idPerson == chat.idPerson }
            ?.run {
                messages.add(message)
            }
            ?: throw PostNotFoundException("Такого чата нет")
        return element.find { it.id == chat.id }?.messages?.last()
    }


    fun createChart(chat: Chats): Chats {
        element.find { it.idPerson == chat.idPerson }
            ?.run { throw PostNotFoundException("Чат с этим пользователем есть") }
            ?: element.add(chat)
        return element.last()
    }
}


class PostNotFoundException(message: String) : RuntimeException(message)

fun main() {
    val message1 = Messages(1, true, false, "first", false)
    val message2 = Messages(2, true, false, "sec", false)
    val message3 = Messages(3, false, true, "third", true)
    val message4 = Messages(4, false, true, "fourth", true)

    val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
    val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
    val chat3 = Chats(3, 3, messages = mutableListOf())

    val service = Messanger()
    service.createChart(chat)
    //service.createChart(chat2)
    //println(service.createChart(chat))
    service.createMessage(chat, message4)
    println(service.getUnreadMessageCount())
    //service.createChart(chat3)
    //println(service.getUnreadMessageCount())
    //println(service.findMessages(chat))
    // println(service.getUnreadMessageCount())
    //println(service.getChat())
    println(service.findMessages(chat))
    println(service.getUnreadMessageCount())
    println(service.findMessages(chat))
    //println(service.getNotEmptyChats())
    //service.findMessages(chat3)
}


