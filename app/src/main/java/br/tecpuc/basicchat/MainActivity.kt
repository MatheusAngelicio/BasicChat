package br.tecpuc.basicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.tecpuc.basicchat.adapter.MessageAdapter
import br.tecpuc.basicchat.databinding.ActivityMainBinding

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

        }
    }

    private fun configureRecyclerView() = with(binding) {
        messageList.layoutManager = LinearLayoutManager(binding.root.context)
        messageList.adapter = MessageAdapter()

    }


}