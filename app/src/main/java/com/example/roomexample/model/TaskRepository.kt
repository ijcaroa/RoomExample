package com.example.roomexample.model

import androidx.lifecycle.LiveData
// conecta la base datos con el view model. Va a buscar los metodos del DAO
class TaskRepository(private val taskDao: TaskDao) {

    //Este value va a contener toda la info de la base de datos
   // Todas estas son acciones que interatuan con la base deatos DAO
    val listAlltask : LiveData<List<TaskEntity>> = taskDao.getAllTask()

    // Permite escribir en la base de datos
    suspend fun insertTask(task: TaskEntity){
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: TaskEntity){
        taskDao.updateTask(task)
    }
    //permite borrar en la base datos
    suspend fun deleteAll(){
        taskDao.deleteAll()
    }

    fun getTaskById(id: Int): LiveData<TaskEntity> {
        return taskDao.getTaskById(id)
    }
}