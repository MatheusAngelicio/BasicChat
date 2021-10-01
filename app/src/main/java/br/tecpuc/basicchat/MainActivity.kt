package br.tecpuc.basicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.tecpuc.basicchat.adapter.MessageAdapter
import br.tecpuc.basicchat.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

const val USER_ID = 0
const val OTHER_ID = 1

class MainActivity : AppCompatActivity() {

    private var fromUser = true

    private lateinit var binding: ActivityMainBinding
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        initViews()
        setupRecyclerView()
    }

    private fun initViews() {
        binding.sendButton.setOnClickListener {
            val messageText = binding.messageEdittext.text.toString()
            binding.messageEdittext.setText("")

            val message = ChatMessage(messageText, if (fromUser) USER_ID else OTHER_ID)
            messageAdapter.addItem(message)
            binding.messageList.scrollToPosition(messageAdapter.itemCount - 1)
            fromUser = !fromUser
        }
    }


    private fun setupRecyclerView() = with(binding) {
        messageList.layoutManager = LinearLayoutManager(binding.root.context)
        messageAdapter = MessageAdapter()
        binding.messageList.adapter = messageAdapter

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