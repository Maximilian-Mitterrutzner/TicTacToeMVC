package tictactoemvc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tictactoemvc.Turn

/**
 * The Entry class is a data class used to represent the data contained in the database
 * as a single object.
 */
@Entity(tableName = "tictactoe_history")
data class Entry (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val p1: String,
        val p2: String,
        val winner: Turn)