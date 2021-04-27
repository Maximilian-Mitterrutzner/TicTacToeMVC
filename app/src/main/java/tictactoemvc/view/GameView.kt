package tictactoemvc.view

import android.view.View
import tictactoemvc.State
import tictactoemvc.Turn

/**
 * The GameView interface is the model responsible for registering listeners on, and relaying
 * actions concerning the main screen to the controller.
 */
interface GameView {
    interface ViewListener {
        fun onPlayerNameChanged(playerOne: Boolean, name: String)

        fun onFieldClicked(field: Int)

        fun onButtonStartPressed()
        fun onButtonResetPressed()
        fun onButtonHistoryPressed()
    }

    fun getRootView(): View
    fun setListener(listener: ViewListener)

    fun setTurn(turn: Turn)
    fun setButtonStartEnabled(boolean: Boolean)
    fun setButtonResetEnabled(boolean: Boolean)
    fun setButtonHistoryEnabled(boolean: Boolean)
    fun updateBoard(fields: Array<State>)
    fun lockNames()
    fun unlockNames()
}