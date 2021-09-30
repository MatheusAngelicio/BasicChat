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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        initViews()
        configureRecyclerView()
    }

    private fun initViews() {
        binding.sendButton.setOnClickListener {
            val messageText = binding.messageEdittext.text.toString()
            binding.messageEdittext.setText("")

            val adapter = binding.messageList.adapter
            if (adapter is MessageAdapter) {
                val message = ChatMessage(messageText, if (fromUser) USER_ID else OTHER_ID)
                adapter.addItem(message)
                binding.messageList.scrollToPosition(adapter.itemCount - 1)
                fromUser = !fromUser
            }

        }
    }

    private fun configureRecyclerView() = with(binding) {
        messageList.layoutManager = LinearLayoutManager(binding.root.context)
        messageList.adapter = MessageAdapter()

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