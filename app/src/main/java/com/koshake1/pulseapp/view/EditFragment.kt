package com.koshake1.pulseapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.koshake1.pulseapp.R
import com.koshake1.pulseapp.databinding.FragmentEditBinding
import com.koshake1.pulseapp.model.data.Note
import com.koshake1.pulseapp.viewmodel.NoteViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditFragment : Fragment(R.layout.fragment_edit) {

    companion object {
        const val NOTE_KEY = "Note"

        fun create(note: Note? = null): EditFragment {
            return EditFragment().also {
                val arguments = Bundle()
                arguments.putParcelable(NOTE_KEY, note)
                it.arguments = arguments
            }
        }
    }

    private val note: Note? by lazy(LazyThreadSafetyMode.NONE) { arguments?.getParcelable(NOTE_KEY) }
    private var _binding: FragmentEditBinding? = null
    private val binding: FragmentEditBinding get() = _binding!!

    private val viewModel by viewModel<NoteViewModel> {
        parametersOf(note)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        with(binding) {
            viewModel.note?.let {
                editPressure.setText(it.pressure)
                editPulse.setText(it.pulse)
            }

            (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
            editPressure.addTextChangedListener {
                viewModel.updatePressure(it?.toString() ?: "")
            }

            editPulse.addTextChangedListener {
                viewModel.updatePulse(it?.toString() ?: "")
            }

            buttonSave.setOnClickListener {
                viewModel.saveNote()
            }
        }
    }
}