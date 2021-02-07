package com.example.roomexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.databinding.TaskItemListBinding
import com.example.roomexample.model.TaskEntity
                    //4. Extender para crear los 3 metodos
class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>() {
   //1
    private var listTask = listOf<TaskEntity>()
    //crear variable selecteditem
    private val selectedTaskItem = MutableLiveData<TaskEntity>()
    //Crear metodo selectedItem que expone el selectedTaskItem
    fun selectedItem():LiveData<TaskEntity> = selectedTaskItem

//2
    fun update(list: List<TaskEntity>){
        listTask = list
    //9 incluir el notify
    notifyDataSetChanged()
    }
    //3 inner class permite entrar a otra clase
    inner class TaskVH(private val binding:TaskItemListBinding):
            RecyclerView.ViewHolder(binding.root),View.OnClickListener {
               fun bind(task:TaskEntity){
                    binding.tvTitle.text=task.title
                    binding.tvDescription.text=task.description
                    binding.tvAuthor.text=task.author
                   itemView.setOnClickListener(this)
                }

        override fun onClick(v: View?) {
            selectedTaskItem.value = listTask[adapterPosition]
        }
    }
   //5.  completar los metodos
                        //7. desde el return
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
       return TaskVH(TaskItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }
    //8 se incluye el val task = listTask = [position]
    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = listTask[position]
            holder.bind(task)
    }
//6 incluir el tamaño listTask.size
    override fun getItemCount(): Int = listTask.size



}