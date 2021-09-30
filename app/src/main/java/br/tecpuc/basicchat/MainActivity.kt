package br.tecpuc.basicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.tecpuc.basicchat.adapter.MessageAdapter
import br.tecpuc.basicchat.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
            val message = binding.messageEdittext.text.toString()

            val adapter = binding.messageList.adapter
            (adapter as? MessageAdapter)?.addItem(message)
            binding.messageList.scrollToPosition((adapter?.itemCount ?: return@setOnClickListener) -1)

            binding.messageEdittext.setText("")
        }
    }

    private fun configureRecyclerView() = with(binding) {
        messageList.layoutManager = LinearLayoutManager(binding.root.context)
        messageList.adapter = MessageAdapter()

    }


}