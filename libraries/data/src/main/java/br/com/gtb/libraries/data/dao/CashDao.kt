package br.com.gtb.libraries.data.dao

import br.com.gtb.libraries.data.entity.CashEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface CashDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cash: CashEntity)

    @Transaction
    suspend fun clearAndInsert(cash: CashEntity) {
        deleteAll()
        insert(cash)
    }

    @Query(
        """
    SELECT * FROM cash
    """
    )
    fun getList(): LiveData<CashEntity?>

    @Query(
        """
    DELETE FROM cash
    """
    )
    fun deleteAll()

}