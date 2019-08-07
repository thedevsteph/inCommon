package yass.stephanie.com.incommon.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yass.stephanie.com.incommon.Messages.MessageListAdapter
import yass.stephanie.com.incommon.R
import yass.stephanie.com.incommon.Utils.ViewHelper


class MessagesFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
        private lateinit var recyclerView: RecyclerView
        private lateinit var viewAdapter: RecyclerView.Adapter<*>
        private lateinit var viewManager: RecyclerView.LayoutManager
        private var viewHelper: ViewHelper = ViewHelper()
    }

    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewManager = LinearLayoutManager(context)
        viewAdapter = MessageListAdapter(viewHelper.generateMockMessageList())

        var heldView =  inflater.inflate(R.layout.messages_fragment, container, false)

        heldView?.let {
            recyclerView = it.findViewById<RecyclerView>(R.id.message_list_recyclerview).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }

        return heldView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        // TODO: Use the ViewModelbu
    }

}
