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
        var count = 0
        for (chat in element) {
            count = chat.messages.count( {it.unread == read} )
        }
        return "Колличество чатов с непрочитанными сообщениями = $count"
    }

    //посмотреть как работает функция
    fun getChat() {
       println( element.filter { it.messages.size > 0 })
    }


    fun findMessages(idChats: Int) {
        for (chart in element) {
            for (message in chart.messages) {
            message.unread = true
        }
            print(chart.messages)
            println(chart.messages.last().id)
            println(chart.messages.size)

        }
    }

    fun createMessage(id: Int, idPerson: Int, message: Messages) {
        element.find { it.id == id && it.idPerson == idPerson }?.messages?.add(message)
    }

    fun createChart(id: Int, chat: Chats) {
       // element.f { it.idPerson != id }?.add(chat)
      element += chat
       /* element.find { it.idPerson == id }
        println("Чат с этим человеком уже есть" )*/
    }
}

fun main() {
    val message1 = Messages(1, true, false, "first", false)
    val message2 = Messages(2, false, true, "sec", false)
    val message3 = Messages(3, false, true, "third", false)
    val message4 = Messages(4, false, true, "fourth", false)

    val chat = Chats(1, 2, messages = mutableListOf(message1, message2))
    val chat2 = Chats(2, 2, messages = mutableListOf(message3, message4))
    val chat3 = Chats(3, 3, messages = mutableListOf())

    val service = Messanger()
    service.createChart(1, chat)
    service.createChart(2, chat2)
    //service.createChart(2,chat3)
    println(service.getUnreadMessageCount())
    /*println(service.findMessages(1))
    println(service.getUnreadMessageCount())*/
    //service.getChat()
}


