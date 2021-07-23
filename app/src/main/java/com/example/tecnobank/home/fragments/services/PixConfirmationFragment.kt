package com.example.tecnobank.home.fragments.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixConfirmationFragmentBinding

class PixConfirmationFragment : Fragment() {

    private var _binding: PixConfirmationFragmentBinding? = null
    private val binding: PixConfirmationFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PixConfirmationFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.btBack.setOnClickListener {
//        }
        binding.pixConfirmationTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_pixConfirmationFragment_to_pixFinishFragment)
        }

        binding.cancelPix.setOnClickListener {
            requireActivity().finish()
        }
    }

}
