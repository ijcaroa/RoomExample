package com.example.roomexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.roomexample.databinding.FragmentSecondBinding
import com.example.roomexample.model.TaskEntity
import com.example.roomexample.viewModel.TaskViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private var idTask: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
             idTask = it.getInt("id",-1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
                saveTask()


            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        viewModel.getTaskById(idTask).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.etTitle.setText(it.title)
                binding.etAuthor.setText(it.author)
                binding.etDescription.setText(it.description)
            }
        })
    }
    private fun saveTask(){
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val author = binding.etAuthor.text.toString()
        if(title.isEmpty() || description.isEmpty() || author.isEmpty()) {
            Toast.makeText(context, "Debe ingresar los campos", Toast.LENGTH_LONG).show()
        } else {
            if (idTask == -1) {
            val newTask = TaskEntity(title = title, description = description, author = author)
            viewModel.insertTask(newTask)
        }else {
            val upDateTask = TaskEntity(id = idTask,
                    title = title,
                    description = description,
                    author = author)
                viewModel.updateTask(upDateTask)
            }

            }

    }

}







