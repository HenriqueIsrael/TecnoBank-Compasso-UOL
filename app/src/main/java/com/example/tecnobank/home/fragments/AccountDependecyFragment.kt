package com.example.tecnobank.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tecnobank.databinding.LiberacaoDeServicosFragmentBinding

class AccountDependecyFragment : Fragment() {

    private var _binding: LiberacaoDeServicosFragmentBinding? = null
    private val binding: LiberacaoDeServicosFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LiberacaoDeServicosFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBack.setOnClickListener {
            requireActivity().finish()
        }

    }

}
