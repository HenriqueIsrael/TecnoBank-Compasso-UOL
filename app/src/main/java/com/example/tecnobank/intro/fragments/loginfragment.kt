package com.example.tecnobank.intro.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tecnobank.R
import com.example.tecnobank.intro.viewmodel.LoginViewModel

class loginfragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

//        view.findViewById<Button>(R.id.login_entrar).setOnClickListener {
//            val user = view.findViewById<EditText>(R.id.login_email).text.toString()
//            val password = view.findViewById<EditText>(R.id.login_senha).text.toString()
//
//            if(true){
//                mostraErro("Erro","Descrição do erro")
//            }
//        }
    }

    fun mostraErro(titulo: String?, mensagem: String?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        builder.setTitle(titulo)
        builder.setMessage(mensagem)
        builder.show()
    }
}
