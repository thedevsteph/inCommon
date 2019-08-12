package yass.stephanie.com.incommon.Utils

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.apmem.tools.layouts.FlowLayout
import yass.stephanie.com.incommon.Chat.ChatMessageData

class ViewHelper {

    companion object {
        private const val TEXT_VIEW_MARGIN = 10
    }

    fun createProfileTextTag(
        context: Context,
        viewGroup: ViewGroup?,
        string: String,
        backgroundColor: Int,
        fontColor: Int
    ) {
        viewGroup?.let {
            var textView = TextView(context)

            var params = FlowLayout.LayoutParams(
                FlowLayout.LayoutParams.WRAP_CONTENT,
                FlowLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(
                TEXT_VIEW_MARGIN,
                TEXT_VIEW_MARGIN,
                TEXT_VIEW_MARGIN,
                TEXT_VIEW_MARGIN
            )
            textView.layoutParams = params

            textView.apply {
                text = string
                setBackgroundResource(backgroundColor)
                setPadding(24, 16, 24, 16)
                setTextColor(ContextCompat.getColor(context, fontColor))
                textSize = 16f
            }
            viewGroup.addView(textView)
        }
    }

    fun getMockInterestData(): ArrayList<String> {
        return arrayListOf(
            "Music",
            "Art",
            "Rowing",
            "Politics",
            "Cycling",
            "International Relations",
            "Drag",
            "Android development"
        )
    }

    fun generateMockMessageList(): ArrayList<MutableMap<String, String>> {
        var mockData = arrayListOf<MutableMap<String, String>>()
        var singleMessage = mutableMapOf<String, String>()
        singleMessage["imageLink"] = "http://www.imageLink.com"
        singleMessage["userName"] = "James Thompson"
        singleMessage["lastMessage"] = "Where are you?"
        singleMessage["lastMessageDate"] = "15 Jun"

        var singleMessage2 = mutableMapOf<String, String>()
        singleMessage2["imageLink"] = "http://www.imageLink.com"
        singleMessage2["userName"] = "Yoko"
        singleMessage2["lastMessage"] = "Meeting Mary at "
        singleMessage2["lastMessageDate"] = "10 Jun"

        for (i in 1..5) {
            mockData.apply {
                add(singleMessage)
                add(singleMessage2)
            }
        }
        return mockData
    }

    fun generateMockChatMessages(): MutableList<ChatMessageData> {

        var receivedMessage = ChatMessageData(
            false,
            "Hi there, Bro",
            "James",
            "4:15pm"
        )


        var sentMessageData = ChatMessageData(
            true,
            "Hi there, James!",
            "You",
            "4:20pm"
        )


        var sentMessage2 = ChatMessageData(
            true,
            "K",
            "You",
            "4:20pm"
        )


        return mutableListOf(
            receivedMessage,
            sentMessageData,
            sentMessageData,
            receivedMessage,
            sentMessageData,
            sentMessage2
        )
    }


}