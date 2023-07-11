package com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentCreateCollectionBinding
import com.yusufcansenturk.moviecatchapp.util.toast
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCollectionFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCreateCollectionBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return if (this::binding.isInitialized){
            binding.root
        }else {
            binding = FragmentCreateCollectionBinding.inflate(layoutInflater)
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener {
            if (validation()) {
                viewModel.createCollection(binding.txtCollectionName.text.toString())
                toast("${binding.txtCollectionName.text.toString()} collection created successfully")
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_createCollectionFragment_to_favoriteFragment)
        }


    }

    private fun validation() : Boolean {
        var isValid = true
        if (binding.txtCollectionName.text.toString().isNullOrEmpty()) {
            isValid = false
            toast("Enter collection name")
        }
        return isValid
    }

}