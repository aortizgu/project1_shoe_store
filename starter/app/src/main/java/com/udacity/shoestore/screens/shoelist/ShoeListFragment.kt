package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.databinding.LayoutShoeBinding
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoelistBinding
    private val viewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, container, false
        )
        setHasOptionsMenu(true)
        binding.floatingActionButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newShoeList ->
            newShoeList.forEach { shoe ->
                val layoutShoeBinding = DataBindingUtil.inflate<LayoutShoeBinding>(
                    inflater,
                    R.layout.layout_shoe,
                    container,
                    false
                )
                layoutShoeBinding.shoe = shoe
                binding.listLayout.addView(layoutShoeBinding.root)
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.i("onCreateOptionsMenu")
        inflater.inflate(R.menu.menu_overflow, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected")
        if (item.itemId == R.id.logoutMenuItem) {
            findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}