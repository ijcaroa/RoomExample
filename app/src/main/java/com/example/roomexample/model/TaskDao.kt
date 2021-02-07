package com.example.roomexample.model

import androidx.lifecycle.LiveData
import androidx.room.*

//Conversa con la base de datos, solo se declaran metodos
@Dao  //Siempre debe ponerse en el DAO
interface TaskDao {
    //Se le pasa el nombre de la clase Taskentity que se creó
    //Inserta una tarea
    //cuando se llama a cualquiera de las funcioens se aplica lo que corresponda segun los crud
        @Insert(onConflict = OnConflictStrategy.REPLACE) //con esto habla con la SQLite
      suspend fun insertTask (task: TaskEntity)
    //inserta un listado de tareas
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAllTask(ListTask: List<TaskEntity>)
    //update
        @Update
       suspend fun updateTask(task: TaskEntity)

    //Borrar una tarea
        @Delete
        suspend fun deleteTask(task: TaskEntity)

    //Borrar todos los elementos de la tabla

        @Query("DELETE FROM task_table")
        suspend fun deleteAll()
    //Traer todos los elementos de la tabla. LEER a todos los get se le agrega}
        //LiveData para mostrarselos al patrón observador
        @Query("SELECT * FROM task_table ORDER BY id DESC")
        fun getAllTask() : LiveData<List<TaskEntity>>

    //Encuetra una tarea por el titulo y limita a una respuesta
        @Query("SELECT * FROM task_table WHERE title = :title LIMIT 1")
        fun getTaskByTitle(title: String) : LiveData<TaskEntity>
    //Encuetra una tarea por id
        @Query("SELECT * FROM task_table WHERE id = :id")
        fun getTaskById(id: Int) : LiveData<TaskEntity>


}