package tictactoemvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.tictactoemvc.R
import tictactoemvc.Turn
import tictactoemvc.model.HistoryDatabase
import tictactoemvc.model.TicTacToe
import tictactoemvc.view.GameView
import tictactoemvc.view.GameViewImp

/**
 * The MainActivity is the controller responsible for tying the view and model together.
 * It handles and relays actions.
 */
class MainActivity : AppCompatActivity(), GameView.ViewListener {
    private lateinit var game: TicTacToe
    private lateinit var view: GameViewImp
    private lateinit var playerNames: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        game = TicTacToe()

        view = GameViewImp(layoutInflater)
        setContentView(view.getRootView())
        view.setListener(this)

        playerNames = arrayOf("Spieler Eins", "Spieler Zwei")

        HistoryDatabase.instance = Room.databaseBuilder(applicationContext, HistoryDatabase::class.java, "tictactoe_history")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    override fun onPlayerNameChanged(playerOne: Boolean, name: String) {
        playerNames[if(playerOne) 0 else 1] = name
    }

    override fun onFieldClicked(field: Int) {
        if(game.selectField(field)) {
            val turn = game.getTurn()
            view.updateBoard(game.getBoard())
            view.setTurn(turn)
            if(turn == Turn.WIN_P1 || turn == Turn.WIN_P2 || turn == Turn.DRAW) {
                view.setButtonResetEnabled(true)
                HistoryDatabase.instance!!.historyDao().insert(playerNames[0], playerNames[1], turn)
            }
        }
    }

    override fun onButtonStartPressed() {
        game.start()

        view.lockNames()
        view.setTurn(game.getTurn())
        view.setButtonStartEnabled(false)
        view.setButtonHistoryEnabled(false)
    }

    override fun onButtonResetPressed() {
        game.reset()

        view.unlockNames()
        view.updateBoard(game.getBoard())
        view.setTurn(game.getTurn())
        view.setButtonResetEnabled(false)
        view.setButtonStartEnabled(true)
        view.setButtonHistoryEnabled(true)
    }

    override fun onButtonHistoryPressed() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}