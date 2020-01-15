package com.trunghtluu.firebaseapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.trunghtluu.firebaseapp.R
import com.trunghtluu.firebaseapp.model.Message
import com.trunghtluu.firebaseapp.viewmodel.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trunghtluu.firebaseapp.adapter.MessageAdapter
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var messageObserver: Observer<Message>
    private var messageList: ArrayList<Message> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        messageObserver = object : Observer<Message> {
            override fun onChanged(message: Message) {
                messageList.add(message)
                setupRV(messageList)
            }
        }

        viewModel.getLiveData().observe(this, messageObserver)

        val x = Message()
        x.messageContent = "Hello Atlanta!"
        x.messageDate = "12/20/2020"
        x.messageTitle = "Message 4"
        x.userName = "Trung Luu"

        viewModel.sendRealMessage(x)
    }

    private fun addMessage(message: Message) {

        Log.d("TAG_X", "Message: " + message.messageContent)

    }

    private fun setupRV(response: List<Message>) {
        val adapter : MessageAdapter = MessageAdapter(response, this)
        val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        main_rv.addItemDecoration(itemDecoration)
        main_rv.setLayoutManager(LinearLayoutManager(this@MainActivity))
        main_rv.setAdapter(adapter)
    }
}
