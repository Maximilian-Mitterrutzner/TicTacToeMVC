package tictactoemvc.model

import tictactoemvc.State
import tictactoemvc.Turn

/**
 * This class represents a model of the game of TicTacToe.
 * It provides a full implementation of the game and allows playing the game by selecting fields.
 */
class TicTacToe {
    private var turn = Turn.NONE
    private var board = Array(9) {State.NONE}
    private var winner = Turn.NONE

    fun start() {
        turn = Turn.PLAYER_1
    }

    fun reset() {
        turn = Turn.NONE
        winner = Turn.NONE
        board.forEachIndexed { i, _ -> board[i] = State.NONE }
    }

    fun selectField(field: Int): Boolean {
        if(turn == Turn.NONE || turn == Turn.WIN_P1 || turn == Turn.WIN_P2 || turn == Turn.DRAW|| board[field] != State.NONE) {
            return false
        }

        if(turn == Turn.PLAYER_1) {
            board[field] = State.X
            turn = Turn.PLAYER_2
        }
        else {
            board[field] = State.O
            turn = Turn.PLAYER_1
        }

        checkWin()

        return true
    }

    private fun checkWin() {
        for(i in 0..2) {
            if(board[i * 3] != State.NONE && board[i * 3] == board[i * 3 + 1] && board[i * 3] == board[i * 3 + 2]) {
                setWinner(board[i * 3])
                return
            }
            if(board[i] != State.NONE && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                setWinner(board[i])
                return
            }
        }

        if(board[4] != State.NONE) {
            if(board[0] == board[4] && board[0] == board[8]
            || board[2] == board[4] && board[4] == board[6]) {
                setWinner(board[4])
                return
            }
        }

        var isDraw = true
        for (i in 0..8) {
            if(board[i] == State.NONE) {
                isDraw = false
                break
            }
        }
        if(isDraw) {
            turn = Turn.DRAW
        }
    }

    private fun setWinner(state: State) {
        turn = if(state == State.X) Turn.WIN_P1 else Turn.WIN_P2
    }

    fun getBoard(): Array<State> {
        return board
    }

    fun getTurn(): Turn {
        return turn
    }
}