package com.example.lr3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lr3.data.Faculty

@Database(
    entities = [Faculty::class,],
    version = 1,
    exportSchema = false
)
@TypeConverters(DBConverters::class)

abstract class MyDatabase: RoomDatabase(){
    abstract fun MyDAO(): MyDAO

    companion object{
        @Volatile
        private  var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase{
            return INSTANCE ?: synchronized(this){
            buildDataBase(context).also { INSTANCE = it}
            }
        }

        val MIGRATION_2_3 = object : Migration(2,3){
            override fun migrate(db: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }
        }


        private  fun buildDataBase(context: Context) = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "list_database")
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_2_3)
            .build()

    }
}