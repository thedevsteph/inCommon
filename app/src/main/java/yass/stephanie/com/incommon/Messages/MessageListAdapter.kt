package yass.stephanie.com.incommon.Messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import yass.stephanie.com.incommon.R
import androidx.appcompat.app.AppCompatActivity
import android.R.attr.author
import android.content.Context
import android.os.Bundle
import yass.stephanie.com.incommon.Chat.ChatFragment


class MessageListAdapter(val data: ArrayList<MutableMap<String, String>>) :
    RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    class MessageListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val singleMessageUserName: TextView = view.findViewById(R.id.single_message_username)
        val singleMessageMessage: TextView = view.findViewById(R.id.single_message_message_text)
        val singleMessageDate: TextView = view.findViewById(R.id.single_message_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.message_fragment_single_message, parent, false) as View
        return MessageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        holder.singleMessageUserName.text = data[position]["userName"]
        holder.singleMessageMessage.text = data[position]["lastMessage"]
        holder.singleMessageDate.text = data[position]["lastMessageDate"]

        val onClickListener = View.OnClickListener {
            openChatFragment(holder.view.context)
        }

        holder.view.setOnClickListener(onClickListener)


    }

    private fun openChatFragment(context: Context){
        val chatFragment = ChatFragment()
        val activity = context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction().replace(R.id.main_activity_layout, chatFragment)
            .addToBackStack(null).commit()
    }


}