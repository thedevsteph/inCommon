package yass.stephanie.com.incommon.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.apmem.tools.layouts.FlowLayout
import yass.stephanie.com.incommon.R


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
        private const val TEXT_VIEW_MARGIN = 10
    }

    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var currentView = inflater.inflate(R.layout.profile_fragment, container, false)
        populateWithMockData(currentView)
        return currentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)


        // TODO: Use the ViewModel
    }

    private fun createInterestTextView(viewGroup: ViewGroup?, string: String) {
        viewGroup?.let {
            var textView = TextView(context)

            var params = FlowLayout.LayoutParams(
                FlowLayout.LayoutParams.WRAP_CONTENT,
                FlowLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(TEXT_VIEW_MARGIN, TEXT_VIEW_MARGIN, TEXT_VIEW_MARGIN, TEXT_VIEW_MARGIN)
            textView.layoutParams = params

            textView.apply {
                text = string
                setBackgroundResource(R.drawable.ic_profile_fragment_interest_background)
                setPadding(24, 16, 24, 16)
                setTextColor(ContextCompat.getColor(context, R.color.whiteBoy))
                textSize = 18f
            }
            viewGroup.addView(textView)
        }
    }

    private fun populateWithMockData(currentView: View) {

        var interestView = currentView?.findViewById<ViewGroup>(R.id.interest_holder)

        var mockInterestArray = arrayListOf(
            "Music",
            "Art",
            "Rowing",
            "Politics",
            "Cycling",
            "International Relations",
            "Drag"
        )

        for (item in mockInterestArray) {
            createInterestTextView(interestView, item)
        }
    }


}
