package yass.stephanie.com.incommon.Chat

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yass.stephanie.com.incommon.Home.MessagesViewModel
import yass.stephanie.com.incommon.R
import yass.stephanie.com.incommon.Utils.ViewHelper


class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
        private lateinit var recyclerView: RecyclerView
        private lateinit var viewAdapter: RecyclerView.Adapter<*>
        private lateinit var viewManager: RecyclerView.LayoutManager
        private var viewHelper: ViewHelper = ViewHelper()
        private var data: MutableList<ChatMessageData> = viewHelper.generateMockChatMessages()
    }

    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ChatMessagesAdapter(data)

        var heldView = inflater.inflate(R.layout.chat_fragment, container, false)

        heldView?.let {
            recyclerView = it.findViewById<RecyclerView>(R.id.chat_message_recyclerview).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }


        var enterField: EditText = heldView.findViewById(R.id.chat_fragment_enter_text_field)
        enterField.setOnKeyListener { _, keyCode, event ->

            scrolltoBottomOfChat()
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                enterField.setSingleLine(true)
                addNewMessage(enterField.text.toString())
                enterField.text.clear()
                true
            }
            false
        }
        viewAdapter.notifyDataSetChanged()

        return heldView
    }


    private fun scrolltoBottomOfChat() {
        recyclerView.scrollToPosition(data.size - 1)
    }


    private fun addNewMessage(message: String) {
        var newMessage = ChatMessageData(true, message, "You", "Now")
        data.add(newMessage)
    }


}
