package tictactoemvc.view

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.tictactoemvc.R
import tictactoemvc.State
import tictactoemvc.Turn
import tictactoemvc.Turn.*

/**
 * The GameViewImp class provides an implementation for the GameView interface.
 */
class GameViewImp(layoutInflater: LayoutInflater) : GameView {
    private val rootView = layoutInflater.inflate(R.layout.activity_main, null)
    private lateinit var viewListener: GameView.ViewListener

    private var txt_p1: EditText
    private var txt_p2: EditText
    private var fields: Array<ImageView>
    private var txv_turn_win: TextView
    private var img_left: ImageView
    private var img_right: ImageView
    private var btn_start: Button
    private var btn_reset: Button
    private var btn_history: Button

    init {
        txt_p1 = rootView.findViewById(R.id.txt_p1)
        txt_p2 = rootView.findViewById(R.id.txt_p2)
        txv_turn_win = rootView.findViewById(R.id.txv_turn_win)
        img_left = rootView.findViewById(R.id.img_left)
        img_right = rootView.findViewById(R.id.img_right)
        btn_start = rootView.findViewById(R.id.btn_start)
        btn_reset = rootView.findViewById(R.id.btn_reset)
        btn_history = rootView.findViewById(R.id.btn_history)

        txt_p1.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    viewListener.onPlayerNameChanged(true, v!!.text.toString())
                    return true
                }
                return false
            }
        })
        txt_p2.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    viewListener.onPlayerNameChanged(false, v!!.text.toString())
                    return true
                }
                return false
            }
        })

        val fieldIds = arrayOf(R.id.img_0, R.id.img_1, R.id.img_2, R.id.img_3, R.id.img_4, R.id.img_5, R.id.img_6, R.id.img_7, R.id.img_8)
        fields = Array(9) { i -> rootView.findViewById<ImageView>(fieldIds[i]) }
        for (i in 0..8) {
            fields[i].setOnClickListener { run { viewListener.onFieldClicked(i) } }
        }

        btn_start.setOnClickListener {
            viewListener.onButtonStartPressed()
        }
        btn_reset.setOnClickListener {
            viewListener.onButtonResetPressed()
        }
        btn_history.setOnClickListener { run { viewListener.onButtonHistoryPressed() }}

        btn_reset.isEnabled = false
        img_left.visibility = View.INVISIBLE
        img_right.visibility = View.INVISIBLE
        txv_turn_win.text = ""
    }

    override fun getRootView(): View = rootView

    override fun setListener(listener: GameView.ViewListener) {
        viewListener = listener
    }

    @SuppressLint("SetTextI18n")
    override fun setTurn(turn: Turn) {
        img_left.visibility = View.INVISIBLE
        img_right.visibility = View.INVISIBLE
        txv_turn_win.text = "Am Zug"

        when (turn) {
            PLAYER_1 -> img_left.visibility = View.VISIBLE
            PLAYER_2 -> img_right.visibility = View.VISIBLE
            WIN_P1 -> txv_turn_win.text = "${txt_p1.text} hat gewonnen!"
            WIN_P2 -> txv_turn_win.text = "${txt_p2.text} hat gewonnen!"
            DRAW -> txv_turn_win.text = "Unentschieden!"
            NONE -> txv_turn_win.text = ""
        }
    }

    override fun setButtonStartEnabled(boolean: Boolean) { btn_start.isEnabled = boolean }
    override fun setButtonResetEnabled(boolean: Boolean) { btn_reset.isEnabled = boolean }
    override fun setButtonHistoryEnabled(boolean: Boolean) { btn_history.isEnabled = boolean }

    override fun updateBoard(fields: Array<State>) {
        fields.forEachIndexed { i, state -> run {
            this.fields[i].setImageResource(when (state) {
                State.X -> R.drawable.x
                State.O -> R.drawable.o
                State.NONE -> android.R.color.transparent
            })
        }}
    }

    override fun lockNames() {
        txt_p1.isEnabled = false
        txt_p2.isEnabled = false
    }

    override fun unlockNames() {
        txt_p1.isEnabled = true
        txt_p2.isEnabled = true
    }
}