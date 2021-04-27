package tictactoemvc.view

import android.view.View
import tictactoemvc.model.Entry

/**
 * The HistoryView interface is the model responsible for registering listeners on, and relaying
 * actions concerning the history screen to the controller.
 */
interface HistoryView {
    interface HistoryListener {
        fun onButtonBackPressed()
        fun onButtonDeleteHistoryPressed()
    }

    fun getRootView(): View
    fun setListener(listener: HistoryListener)

    fun setHistory(items: List<Entry>)
    fun setLeaderboard(entries: List<Pair<String, Int>>)
}