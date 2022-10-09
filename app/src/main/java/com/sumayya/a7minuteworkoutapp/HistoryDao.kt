package com.sumayya.a7minuteworkoutapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Create a dao interface with insert method
@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    // Create a query to fetch the entries
    @Query("SELECT * FROM `history-table`")
    fun fetchALlDates():Flow<List<HistoryEntity>>

}