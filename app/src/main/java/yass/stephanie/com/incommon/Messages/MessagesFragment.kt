package yass.stephanie.com.incommon.Home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yass.stephanie.com.incommon.R


class MessagesFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
    }

    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.messages_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
