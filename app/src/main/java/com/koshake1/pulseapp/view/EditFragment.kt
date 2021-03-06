package com.koshake1.pulseapp.view

import android.os.Bundle
import android.view.*
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

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)

        (requireActivity() as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        with(binding) {
            viewModel.note?.let {
                editSysPressure.setText(it.sys_pressure)
                editDiasPressure.setText(it.dias_pressure)
                editPulse.setText(it.pulse)
            }

            (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
            editSysPressure.addTextChangedListener {
                viewModel.updateSysPressure(it?.toString() ?: "")
            }

            editDiasPressure.addTextChangedListener {
                viewModel.updateDiasPressure(it?.toString() ?: "")
            }

            editPulse.addTextChangedListener {
                viewModel.updatePulse(it?.toString() ?: "")
            }

            buttonSave.setOnClickListener {
                viewModel.saveNote()
                requireActivity().onBackPressed()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                view?.clearFocus()
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}