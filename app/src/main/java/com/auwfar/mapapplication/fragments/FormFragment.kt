package com.auwfar.mapapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.auwfar.mapapplication.MainViewModel
import com.auwfar.mapapplication.databinding.FragmentFormBinding
import com.auwfar.mapapplication.utils.AppPreferences

class FormFragment : Fragment() {

    private var binding: FragmentFormBinding? = null
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val appPreferences by lazy {
        AppPreferences(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.run {
            tilName.editText?.setText(appPreferences.userName.orEmpty())
            tilHobby.editText?.setText(appPreferences.userHobby.orEmpty())
            btnSubmit.setOnClickListener {
                if (isValidationSuccess()) {
                    tilHobby.isErrorEnabled = false
                    tilName.isErrorEnabled = false

                    val name = tilName.editText?.text.toString()
                    val hobby = tilHobby.editText?.text.toString()

                    appPreferences.userName = name
                    appPreferences.userHobby = hobby
                    activityViewModel.setUserName(name)

                    Toast.makeText(requireContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidationSuccess(): Boolean {
        var result = true
        binding?.run {
            if (tilName.editText?.text.toString().isBlank()) {
                tilName.error = "Nama tidak boleh kosong"
                result = false
            }
            if (tilHobby.editText?.text.toString().isBlank()) {
                tilHobby.error = "Hobi tidak boleh kosong"
                result = false
            }
        }
        return result
    }
}