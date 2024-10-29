package br.com.gtb.libraries.data

import android.content.Context
import android.util.Log
import br.com.gtb.libraries.data.dao.CashDao
import br.com.gtb.libraries.data.dao.ProductDao
import br.com.gtb.libraries.data.dao.SpotlightDao
import br.com.gtb.libraries.data.entity.CashEntity
import br.com.gtb.libraries.data.entity.ProductEntity
import br.com.gtb.libraries.data.entity.SpotlightEntity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        CashEntity::class,
        SpotlightEntity::class,
        ProductEntity::class,
    ], version = 3, exportSchema = true
)
abstract class ArchitectureDatabase : RoomDatabase() {
    abstract val cashDao: CashDao
    abstract val spotlightDao: SpotlightDao
    abstract val productDao: ProductDao
}

private lateinit var instance: ArchitectureDatabase

fun getDatabase(context: Context): ArchitectureDatabase {
    synchronized(ArchitectureDatabase::class.java) {
        if (!::instance.isInitialized) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                ArchitectureDatabase::class.java,
                "architecture_database.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return instance
}

fun ArchitectureDatabase.clearDatabase() {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            cashDao.deleteAll()
            spotlightDao.deleteAll()
            productDao.deleteAll()
        } catch (ex: Exception) {
            Log.e("Digio-Error", ex.toString())
        }
    }
}