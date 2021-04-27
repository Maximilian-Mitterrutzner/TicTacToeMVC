package tictactoemvc

/**
 * The Turn class represents all states the game can enter, including:
 * PLAYER_1: It's the first player's turn.
 * PLAYER_2: It's the second player's turn.
 * WIN_P1:   The game has ended with a win for player one.
 * WIN_P2:   The game has ended with a win for player two.
 * DRAW:     The game has ended with a draw.
 * NONE:     The game has not yet started or has been reset after a win/draw.
 */
enum class Turn {
    PLAYER_1,
    PLAYER_2,
    WIN_P1,
    WIN_P2,
    DRAW,
    NONE
}