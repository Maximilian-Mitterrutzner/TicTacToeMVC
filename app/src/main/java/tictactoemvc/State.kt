package tictactoemvc

/**
 * The State class represents the three basic states a single field on the board can have:
 * X:    Checked by player one.
 * O:    Checked by player two.
 * NONE: Not checked by either player (yet).
 */
enum class State {
    X,
    O,
    NONE
}