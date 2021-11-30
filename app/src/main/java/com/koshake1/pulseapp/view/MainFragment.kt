package com.koshake1.pulseapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.koshake1.pulseapp.R
import com.koshake1.pulseapp.databinding.FragmentMainBinding
import com.koshake1.pulseapp.model.data.Note
import com.koshake1.pulseapp.view.adapter.NotesAdapter
import com.koshake1.pulseapp.viewmodel.DataViewState
import com.koshake1.pulseapp.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)

        val adapter = NotesAdapter {
            navigateToNote(it)
        }

        binding.mainRecycler.adapter = adapter

        //viewModel.observeViewState().observe(viewLifecycleOwner) {
        //    when (it) {
        //        is DataViewState.Value -> {
        //            adapter.submitList(it.notes)
        //        }
        //        DataViewState.EMPTY -> Unit
        //    }
        //}

        binding.fab.setOnClickListener {
            navigateToCreation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToNote(note: Note) {
        (requireActivity() as MainActivity).navigateTo(EditFragment.create(note))
    }

    private fun navigateToCreation() {
        (requireActivity() as MainActivity).navigateTo(EditFragment.create(null))
    }
}