package com.vinh.demopaypal.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.vinh.demopaypal.data.entinies.Beer

@Dao
interface BeerDao : BaseDao<Beer> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(entities: List<Beer>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateIgnore(entities: List<Beer>)

    @Query("DELETE FROM Beer")
    fun deleteAll()

    @Query("SELECT * FROM Beer")
    fun liveData(): LiveData<List<Beer>>
}