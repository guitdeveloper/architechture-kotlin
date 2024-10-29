package br.com.gtb.libraries.data.dao

import br.com.gtb.libraries.data.entity.ProductEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ProductEntity>)

    @Transaction
    suspend fun clearAndInsert(list: List<ProductEntity>) {
        deleteAll()
        insert(list)
    }

    @Query(
        """
        SELECT * FROM products
        """
    )
    fun getList(): LiveData<List<ProductEntity>?>

    @Query(
        """
        DELETE FROM products
        """
    )
    fun deleteAll()

}