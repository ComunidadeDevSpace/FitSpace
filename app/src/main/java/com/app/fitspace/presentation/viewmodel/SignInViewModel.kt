package com.app.fitspace.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.app.fitspace.data.local.AppDatabase
import com.app.fitspace.data.model.User

class SignInViewModel(
    application: Application
) : AndroidViewModel(application) {


    // função para validar conexão com internet
    private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val network = connectivityManager.activeNetwork
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                return networkCapabilities != null &&
                        (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }

    //Função para validar e autenticar usuários na ROOM

    class AuthViewModel(private val context: Context) : ViewModel() {

        private val database = AppDatabase.getInstance(context)
        private val userDao = database.userDao()
        fun login(email: String, password: String): Boolean {
            val user = userDao.getUserByEmailAndPassword(email, password);

            if(email != null && password == password) {
                Toast.makeText(context, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "E-mail ou senha não conferem!", Toast.LENGTH_SHORT).show();
            }
            return user != null
        }
    //Caso o usuario não for registrado, esta função permite que o usuário possa ser registrado. Pode-se criar uma Intent para levar à tela de registro
        fun register(email: String, password: String) {
            val newUser = User(email = email,
                               password = password,
                               birth = "",
                               gender = "",
                               name = "",
                               nickname = "",
                               phone = "");
            userDao.insertUser(newUser)
        }
    }

}