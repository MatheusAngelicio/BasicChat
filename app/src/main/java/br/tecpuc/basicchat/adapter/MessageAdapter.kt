package br.tecpuc.basicchat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.tecpuc.basicchat.ChatMessage
import br.tecpuc.basicchat.R
import br.tecpuc.basicchat.USER_ID
import kotlinx.android.synthetic.main.sent_card.view.*

const val SENT_MESSAGE = 0
const val RECEIVED_MESSAGE = 1

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    private val items: MutableList<ChatMessage> = mutableListOf()

    fun addItem(message: ChatMessage) {
        items.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) =
        if (items[position].senderId == USER_ID) {
            SENT_MESSAGE
        } else RECEIVED_MESSAGE


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(
            if (viewType == SENT_MESSAGE)
                R.layout.sent_card else R.layout.received_card, parent, false)
        return MessageViewHolder(card)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentItem = items[position]
        holder.messageTextView.text = currentItem.text
        holder.momentTextView.text = currentItem.moment
    }

}

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val messageTextView = itemView.messageTextView
    val momentTextView = itemView.moment_textview
}