package com.app.fitspace.presentation.viewmodel

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import com.app.fitspace.data.local.AppDatabase
import com.app.fitspace.data.local.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SignInViewModel(
    application: Application
) : AndroidViewModel(application) {

    private lateinit var userDao: UserDao


    init {
        userDao = AppDatabase.getInstance(application).userDao()
    }

    suspend fun validaLoginPass(emailEditText: EditText, passwordEditText: EditText): Boolean {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (email.isBlank() || password.isBlank()) return false

        return withContext(Dispatchers.IO) {
            val user = userDao.getUserByEmailAndPassword(email, password)
            user != null
        }
    }

    // função para validar conexão com internet
//    private fun isNetworkAvailable(context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val network = connectivityManager.activeNetwork
//            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
//            return networkCapabilities != null &&
//                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
//        } else {
//            val activeNetworkInfo = connectivityManager.activeNetworkInfo
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected
//        }
//    }


}