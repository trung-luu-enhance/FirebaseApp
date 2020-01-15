package com.trunghtluu.firebaseapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trunghtluu.firebaseapp.R
import com.trunghtluu.firebaseapp.model.Message

import kotlinx.android.synthetic.main.message_card.*
import kotlinx.android.synthetic.main.message_card.view.*

class MessageAdapter( var messageList: List<Message>,
                      var context : Context) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    init {
        this.messageList = messageList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MessageViewHolder {
        context = parent.context.applicationContext

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_card, parent, false)

        return MessageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.userTextView.setText(messageList.get(position).userName)
        holder.dateTextView.setText(messageList.get(position).messageDate)
        holder.titleTextView.setText(messageList.get(position).messageTitle)
        holder.contentTextView.setText(messageList.get(position).messageContent)
    }

    class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val userTextView = itemView.user_tv
        val dateTextView = itemView.date_tv
        val titleTextView = itemView.title_tv
        val contentTextView = itemView.content_tv
    }
}