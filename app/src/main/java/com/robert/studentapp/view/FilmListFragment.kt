package com.robert.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.studentapp.R
import com.robert.studentapp.databinding.FragmentFilmListBinding
import com.robert.studentapp.viewmodel.FilmDetailModel

class FilmListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentFilmListBinding
    private lateinit var viewModel: FilmDetailModel
    private val filmListAdapter = FilmListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FilmDetailModel::class.java)
        viewModel.refresh()

        observeViewModel()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = filmListAdapter

        binding.recView.visibility = View.VISIBLE

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFilmListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root





    }

    fun observeViewModel() {
        viewModel.filmLD.observe(viewLifecycleOwner, Observer {
            filmListAdapter.updateFilmList(it)
        })

        viewModel.filmLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE

            } else {
                binding.progressLoad.visibility = View.GONE
                binding.recView.visibility = View.VISIBLE

            }
        })
    }
}