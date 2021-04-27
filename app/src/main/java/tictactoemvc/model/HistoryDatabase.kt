package tictactoemvc.model

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The HistoryDatabase class represents the database used for storing the match history.
 * It also provides access to the DAO and to itself trough a companion object.
 */
@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDAO

    companion object {
        @Volatile
        var instance: HistoryDatabase? = null
    }
}