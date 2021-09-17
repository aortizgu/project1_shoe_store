package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoedetailsBinding
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoedetailsBinding
    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoedetails, container, false
        )
        binding.viewModel = viewModel
        binding.button.setOnClickListener { view: View ->
            viewModel.addShoe()
            view.findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
        }
        viewModel.shoeAdded.observe(viewLifecycleOwner, { added ->
            if (added) {
                findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
                viewModel.onShoeAdded()
            }
        })
        return binding.root
    }
}