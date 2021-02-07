package com.example.roomexample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.databinding.FragmentFirstBinding
import com.example.roomexample.viewModel.TaskViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var binding :FragmentFirstBinding
    //Con la variable viewModel se tendrá acceso
    // a los elementos que espone el viewModel que se hace en el TaskViewModel
    private val viewModel: TaskViewModel by activityViewModels()



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        //observa el livedata.
       //1 variable adapter
        val adapter = TaskAdapter()
        // con esto se le indica al RV cual es el adapter
        binding.recyclerView.adapter = adapter
        //2. Se le indica como se quieren mostrar los datos
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration
        (context,DividerItemDecoration.VERTICAL))



        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let{
            // 3 En el observador se le pasa el update que esta en el adapter
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                val bundle = Bundle()
                bundle.putInt("id",it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })

        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

          /* val tarea = TaskEntity(title = "ejemplo Titulo",description = "descripción",
                    author = "ignacio")
            viewModel.insertTask(tarea)*/
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> {
                Toast.makeText(context, "SE ELIMINÓ TODO", Toast.LENGTH_LONG).show()
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}
