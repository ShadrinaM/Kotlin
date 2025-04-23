package com.example.list.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.list.data.Faculty
import com.example.list.data.Group
import com.example.list.data.Student

@Database(
    entities = [Faculty::class, Student::class, Group::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DBConverters::class)

abstract class MyDatabase: RoomDatabase() {
    abstract fun myDAO(): MyDAO

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? =null

        fun getDatabase(context: Context): MyDatabase{
            return INSTANCE ?: synchronized(this){
                buildDatabase(context).also { INSTANCE = it}
            }
        }

//        val MIGRATION_2_3 = object : Migration(2,3) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                ...
//            }
//        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "list_database")
            .fallbackToDestructiveMigration()
         //   .addMigrations()
            .build()
    }
}