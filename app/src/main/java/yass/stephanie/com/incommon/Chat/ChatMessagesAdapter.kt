package yass.stephanie.com.incommon.Chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import yass.stephanie.com.incommon.R


class ChatMessagesAdapter(val data: MutableList<ChatMessageData>) :
    RecyclerView.Adapter<ChatMessagesAdapter.ChatMessagesViewHolder>() {

    companion object {
        lateinit var holder: ChatMessagesViewHolder
    }

    abstract class ChatMessagesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: ChatMessageData)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessagesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(viewType, parent, false)
        when (viewType) {
            R.layout.message_received_chat_bubble -> holder = MessageReceivedViewHolder(view)
            R.layout.message_sent_chat_bubble -> holder = MessageSentViewHolder(view)
        }

        return holder
    }

    override fun getItemViewType(position: Int): Int {
        var isSentMessage = data[position].isSentMessage
        return when (isSentMessage) {
            false -> R.layout.message_received_chat_bubble
            else -> R.layout.message_sent_chat_bubble
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChatMessagesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class MessageReceivedViewHolder(view: View) : ChatMessagesViewHolder(view) {

        private val receivedMessageSender: TextView = view.findViewById(R.id.message_received_sender_name)
        private val receivedMessageContent: TextView = view.findViewById(R.id.message_received_text_content)
        private val receivedMessageTimestamp: TextView = view.findViewById(R.id.message_received_timestamp)

        override fun bind(item: ChatMessageData) {
            receivedMessageContent.text = item.messageContent
            receivedMessageSender.text = item.messageSender
            receivedMessageTimestamp.text = item.messageTimeStamp
        }
    }

    class MessageSentViewHolder(view: View) : ChatMessagesViewHolder(view) {
        private val sentMessageSender: TextView = view.findViewById(R.id.message_sent_sender_name)
        private val sentMessageContent: TextView = view.findViewById(R.id.message_sent_text_content)
        private val sentMessageTimestamp: TextView = view.findViewById(R.id.message_sent_timestamp)

        override fun bind(item: ChatMessageData) {
            sentMessageContent.text = item.messageContent
            sentMessageSender.text = item.messageSender
            sentMessageTimestamp.text = item.messageTimeStamp
        }
    }
}