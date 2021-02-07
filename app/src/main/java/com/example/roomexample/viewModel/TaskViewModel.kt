package com.example.roomexample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomexample.model.TaskDataBase
import com.example.roomexample.model.TaskEntity
import com.example.roomexample.model.TaskRepository
import kotlinx.coroutines.launch

//Se encarga de hablar con el modelo y exponerle datos a la vista
//Application
class TaskViewModel(application: Application):AndroidViewModel(application) {
    //Variable que representa al repositorio
    private val repository: TaskRepository
    //Livedata que expone la info del modelo. esta variable será observada
    val allTask : LiveData<List<TaskEntity>>
    //En el init se inicializan  las 2 variables anteriores
    init {
        val taskDao = TaskDataBase.getDataBase(application).getTaskDao()
        repository = TaskRepository(taskDao) // rEPOSITORIO instanciado
        allTask = repository.listAlltask
    }
            //Aqui se maneja la coroutina.Con esto se puede trabajar contodos
            //los suspend fun
         fun insertTask (task: TaskEntity) = viewModelScope.launch {
             repository.insertTask (task)
  }
    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.updateTask(task)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun getTaskById (id: Int): LiveData<TaskEntity> {
        return repository.getTaskById(id)

    }
}


