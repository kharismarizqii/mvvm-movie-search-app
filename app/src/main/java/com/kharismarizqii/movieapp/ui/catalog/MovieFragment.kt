package com.kharismarizqii.movieapp.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.movieapp.R
import com.kharismarizqii.movieapp.databinding.FragmentCatalogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_catalog){
    private val viewModel by  viewModels<MovieViewModel>()

    private var _binding : FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCatalogBinding.bind(view)

        val adapter = MovieAdapter()

        binding.apply {
            rvCatalog.setHasFixedSize(true)
            rvCatalog.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter {adapter.retry()},
                footer = MovieLoadStateAdapter {adapter.retry()}
            )
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}