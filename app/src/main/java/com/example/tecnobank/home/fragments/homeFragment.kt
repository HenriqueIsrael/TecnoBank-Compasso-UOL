package com.example.tecnobank.home.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R
import com.example.tecnobank.home.adapter.ViewPagerAdapter
import com.example.tecnobank.data.remote.model.home.BalanceBenefitsResponse
import com.example.tecnobank.databinding.HomeFragmentBinding
import com.example.tecnobank.extension.ExtensionFunctions.Companion.addDecimalCases
import com.example.tecnobank.extension.ExtensionFunctions.Companion.converterStringToReal
import com.example.tecnobank.home.recyclerview.ListBenefitsAdapter
import com.example.tecnobank.home.recyclerview.PagerDecoratorDots
import com.example.tecnobank.home.viewmodel.HomeViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import java.text.NumberFormat


class homeFragment:Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private lateinit var viewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(HomeViewModel::class.java)

        viewModel.onOpenHome()

        viewModel.responseSucess.observe(viewLifecycleOwner, {
            binding.cardBenefitsAndHelp.isVisible = true
            binding.valorSaldo.text = converterStringToReal(it.balance.currentValue)
            binding.valorVendas.text = addDecimalCases(it.balance.receivables)
            recyclerViewConfig(it.benefits)
        })

        viewModel.responseErro.observe(viewLifecycleOwner, {
            showInfo(it)
        })

        binding.btVisibleBalance.setOnClickListener {
            viewModel.checkVisibleBalances()
        }

        binding.incompletSingUp.setOnClickListener {
            binding.funcionalidadesFaltaPoucoBanner.isVisible = false
        }

        viewModel.visibleBalancesOn.observe(viewLifecycleOwner, {
            binding.valorSaldo.setTransformationMethod(null)
            binding.valorVendas.setTransformationMethod(null)
        })

        viewModel.visibleBalancesOff.observe(viewLifecycleOwner, {
            binding.valorSaldo.setTransformationMethod(PasswordTransformationMethod())
            binding.valorVendas.setTransformationMethod(PasswordTransformationMethod())
        })

        viewPagerAndTabLayoutConfig()
    }

    private fun viewPagerAndTabLayoutConfig() {
        binding.pagerFunctionalities.adapter = ViewPagerAdapter(this)
        tabLayoutConfig()
    }

    private fun tabLayoutConfig() {
        TabLayoutMediator(
            binding.tabLayoutFunctionalities,
            binding.pagerFunctionalities
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.main_services)
                }
                1 -> {
                    tab.text = getString(R.string.products_services)
                }
                2 -> {
                    tab.text = getString(R.string.service_services)
                }
            }
        }.attach()
    }

    private fun recyclerViewConfig(listBenefitsResponse: List<BalanceBenefitsResponse.Benefits>) {
        with(binding.listBenefits) {
            adapter = ListBenefitsAdapter(listBenefitsResponse)
            dotsConfig()
        }
    }

    private fun RecyclerView.dotsConfig() {
        val decor = PagerDecoratorDots()
        addItemDecoration(decor)
        addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(
                rv: RecyclerView,
                motionEvent: MotionEvent
            ): Boolean {
                return decor.isIndicatorPressing(motionEvent, rv)
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }

    fun showInfo(titulo: String?) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(titulo)
            .setMessage("")
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

