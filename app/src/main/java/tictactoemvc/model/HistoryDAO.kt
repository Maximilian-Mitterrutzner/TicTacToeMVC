package tictactoemvc.model

import androidx.room.Dao
import androidx.room.Query
import tictactoemvc.Turn

/**
 * The HistoryDAO class is a Data Access Object used to interact with the database.
 */
@Dao
interface HistoryDAO {
    @Query("insert into tictactoe_history values(null, :p1, :p2, :winner)")
    fun insert(p1: String, p2: String, winner: Turn)

    @Query("select * from tictactoe_history")
    fun getAll(): List<Entry>

    @Query("delete from tictactoe_history")
    fun deleteAll()
}