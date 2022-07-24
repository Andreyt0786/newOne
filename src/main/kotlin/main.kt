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

    /*fun add(elem: T): T {
        element += elem
        return element.last()
    }*/

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

    fun getUnreadMessageCount(read: Boolean = false): String {
        val countMessages = element.flatMap {
            it.messages
        }
            .count { it.unread == read && it.import == true }

        val countChat = element.count { chat ->
            chat.messages.any() { it.unread == read }
        }
        return " Количество не прочитанных сообщений равно : $countMessages, в $countChat чатах"
    }


    fun getChat(chat: Chats) {
        element.find { it.id == chat.id && it.messages.size > 0}
            ?.let {
                println(chat)
            }
            ?: run { println("Сообщений нет") }
    }


    fun findMessages(chat: Chats) {

        element.find { it.id == chat.id }
            ?.let {
                println("Чат id= ${chat.id} ")
                println("Колличество сообщений ${element.find { it.id == chat.id }?.messages?.count()}")
                println("Последнее сообщение - ${element.find { it.id == chat.id }?.messages?.find { it.id.last }}")
                element.find{it.id == chat.id}?.messages?.find{it.unread == false}?.unread=true
            }
            ?: run { println("Чата с таким id ${chat.id} нет") }
           }

    fun createMessage(chat: Chats, message: Messages) {
        element.find { it.id == chat.id && it.idPerson == chat.idPerson }
            ?.run { messages.add(message) }
            ?: let {
                println("Такого чата нет")
            }
    }


    fun createChart(chat: Chats) {
        element.find { it.idPerson == chat.idPerson }
            ?.let { println("Чат с этим пользователем есть") }
            ?: run { element += chat }
    }
}

fun main() {
    val message1 = Messages(1, true, false, "first", false)
    val message2 = Messages(2, false, true, "sec", false)
    val message3 = Messages(3, false, true, "third", false)
    val message4 = Messages(4, false, true, "fourth", false)

    val chat = Chats(1, 1, messages = mutableListOf(message1, message2))
    val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
    val chat3 = Chats(3, 3, messages = mutableListOf())

    val service = Messanger()
    service.createChart(chat)
    //service.createChart(chat2)
    //service.createChart(chat3)
    //println(service.getUnreadMessageCount())
    /*println(service.findMessages(1))
    println(service.getUnreadMessageCount())*/
    // service.getChat()
    service.findMessages(chat)
    println(service.getUnreadMessageCount())

}


