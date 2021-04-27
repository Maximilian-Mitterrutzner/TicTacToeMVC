package tictactoemvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoemvc.R
import tictactoemvc.Turn
import tictactoemvc.model.HistoryDatabase
import tictactoemvc.view.HistoryView
import tictactoemvc.view.HistoryViewImp

/**
 * The HistoryActivity is responsible for controlling the activity which shows the game-history.
 * It handles and relays certain actions.
 */
class HistoryActivity : AppCompatActivity(), HistoryView.HistoryListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val view = HistoryViewImp(layoutInflater)
        view.setListener(this)

        val items = HistoryDatabase.instance!!.historyDao().getAll()
        view.setHistory(items)

        val map = HashMap<String, Int>()
        loop@ for (item in items) {
            val winner = when(item.winner) {
                Turn.WIN_P1 -> item.p1
                Turn.WIN_P2 -> item.p2
                else -> continue@loop
            }

            map[winner] = if(map.containsKey(winner)) map[winner]!! + 1 else 1
        }

        val sorted = map.toList().sortedWith(Comparator { one, two -> one.second.compareTo(two.second)})
        view.setLeaderboard(sorted)

        setContentView(view.getRootView())
    }

    override fun onButtonBackPressed() {
        finish()
    }

    override fun onButtonDeleteHistoryPressed() {
        HistoryDatabase.instance!!.historyDao().deleteAll()

        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}