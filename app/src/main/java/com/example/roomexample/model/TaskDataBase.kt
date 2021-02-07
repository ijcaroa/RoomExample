package com.example.roomexample.model

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//Se inckuye esto, uno por entidad
@Database(entities = [TaskEntity :: class], version = 1)
// Clase abstracta
abstract class TaskDataBase : RoomDatabase(){
// metodo que devuelve los objetos que están en DAO
    abstract fun getTaskDao(): TaskDao // es un metodo por cada interface dao
    //companion object forma de exponer un metodo sin tenerr q instanciar la clase
    companion object {

        @Volatile
        private var INSTANCE : TaskDataBase? = null

        fun getDataBase(context: Context): TaskDataBase{
            val  tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            //Instancia la base de datos
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                     "task_db")
                    .build()
                INSTANCE = instance
                return instance
                
            }
        }
    }
}