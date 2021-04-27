package tictactoemvc.view

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.tictactoemvc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tictactoemvc.Turn.*
import tictactoemvc.model.Entry

/**
 * The HistoryViewImp class provides an implementation for the HistoryView interface.
 */
class HistoryViewImp(layoutInflater: LayoutInflater) : HistoryView {
    private val rootView = layoutInflater.inflate(R.layout.activity_history, null)
    private lateinit var historyListener: HistoryView.HistoryListener

    private var btn_back: FloatingActionButton
    private var btn_deleteHistory: Button
    private var lnr_historyContainer: LinearLayout
    private var tbl_leaderboard: TableLayout

    init {
        btn_back = rootView.findViewById(R.id.btn_back)
        btn_deleteHistory = rootView.findViewById(R.id.btn_deleteHistory)
        lnr_historyContainer = rootView.findViewById(R.id.lnr_history)
        tbl_leaderboard = rootView.findViewById(R.id.tbl_leaderboard)

        btn_back.setOnClickListener {
            historyListener.onButtonBackPressed()
        }
        btn_deleteHistory.setOnClickListener {
            historyListener.onButtonDeleteHistoryPressed()
        }
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun setListener(listener: HistoryView.HistoryListener) {
        this.historyListener = listener
    }

    override fun setHistory(items: List<Entry>) {
        for (item in items) {
            val textView = TextView(rootView.context)
            textView.text = when(item.winner) {
                WIN_P1 -> "${item.p1} gewann gegen ${item.p2}"
                WIN_P2 -> "${item.p1} verlor gegen ${item.p2}"
                DRAW -> "${item.p1} und ${item.p2} erzielten ein Unentschieden"
                else -> "Error"
            }
            textView.textSize = 15f
            textView.gravity = Gravity.CENTER
            lnr_historyContainer.addView(textView)
        }
    }

    override fun setLeaderboard(entries: List<Pair<String, Int>>) {
        for(entry in entries) {
            val txv_winCount = TextView(rootView.context)
            txv_winCount.text = entry.second.toString()
            txv_winCount.textSize = 15f
            txv_winCount.gravity = Gravity.CENTER

            val txv_name = TextView(rootView.context)
            txv_name.text = entry.first
            txv_name.textSize = 15f
            txv_name.gravity = Gravity.CENTER

            val row = TableRow(rootView.context)
            row.addView(txv_winCount)
            row.addView(txv_name)

            tbl_leaderboard.addView(row, 1)
        }
    }
}