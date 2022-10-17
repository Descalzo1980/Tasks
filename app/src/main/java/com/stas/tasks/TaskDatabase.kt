package com.stas.tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){
    abstract val taskDao : TaskDao

    companion object{
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase{
            synchronized(this){
                var instant = INSTANCE
                if(instant == null){
                    instant = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instant
                }
                return instant
            }
        }
    }
}