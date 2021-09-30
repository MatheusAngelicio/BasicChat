package br.tecpuc.basicchat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.tecpuc.basicchat.R
import kotlinx.android.synthetic.main.message_card.view.*

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    val items: List<String> = listOf("Um", "Dois", "Tres")

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.message_card, parent, false)
        return MessageViewHolder(card)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentItem = items[position]
        holder.messageTextView.text = currentItem
    }

}

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val messageTextView = itemView.message_textview
}