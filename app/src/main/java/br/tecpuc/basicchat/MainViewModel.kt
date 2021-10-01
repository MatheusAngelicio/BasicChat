package br.tecpuc.basicchat

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

const val USER_ID = 0
const val OTHER_ID = 1

class MainViewModel : ViewModel() {

    private var fromUser = false

    fun updateNewMessage(message: String): ChatMessage {
        fromUser = !fromUser
        return ChatMessage(message, if (fromUser) USER_ID else OTHER_ID)
    }

}

class ChatMessage(
    val text: String,
    val senderId: Int,
    val timestamp: Long = Date().time
) {
    val moment: String
        get() = SimpleDateFormat("HH:mm").format(timestamp)
}