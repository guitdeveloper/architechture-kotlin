package br.com.gtb.libraries.data.dao

import br.com.gtb.libraries.data.entity.SpotlightEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SpotlightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<SpotlightEntity>)

    @Transaction
    suspend fun clearAndInsert(list: List<SpotlightEntity>) {
        deleteAll()
        insert(list)
    }

    @Query(
        """
        SELECT * FROM spotlights
        """
    )
    fun getList(): LiveData<List<SpotlightEntity>?>

    @Query(
        """
        DELETE FROM spotlights
        """
    )
    fun deleteAll()

}