package br.tecpuc.basicchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.tecpuc.basicchat.adapter.MessageAdapter
import br.tecpuc.basicchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewModel = MainViewModel()

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

            messageAdapter.addItem(viewModel.updateNewMessage(messageText))
            binding.messageList.scrollToPosition(messageAdapter.itemCount - 1)
        }
    }


    private fun setupRecyclerView() = with(binding) {
        messageList.layoutManager = LinearLayoutManager(binding.root.context)
        messageAdapter = MessageAdapter()
        binding.messageList.adapter = messageAdapter

    }

}

