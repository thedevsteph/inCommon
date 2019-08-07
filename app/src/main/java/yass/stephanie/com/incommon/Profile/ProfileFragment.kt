package yass.stephanie.com.incommon.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import yass.stephanie.com.incommon.R
import yass.stephanie.com.incommon.Utils.ViewHelper


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
        private const val TEXT_VIEW_MARGIN = 10
        private val viewHelper = ViewHelper()
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

    private fun populateWithMockData(currentView: View) {

        var interestView = currentView.findViewById<ViewGroup>(R.id.interest_holder)
        var petPeeveView = currentView.findViewById<ViewGroup>(R.id.petpeeve_holder)

        var mockInterestArray = viewHelper.getMockInterestData()

        for (item in mockInterestArray) {
            viewHelper.createProfileTextTag(
                context!!,
                interestView,
                item,
                R.drawable.ic_profile_fragment_interest_background,
                R.color.whiteBoy
            )
            viewHelper.createProfileTextTag(
                context!!,
                petPeeveView,
                item,
                R.drawable.ic_profile_fragment_petpeeve_background,
                R.color.whiteBoy
            )
        }
    }
}
