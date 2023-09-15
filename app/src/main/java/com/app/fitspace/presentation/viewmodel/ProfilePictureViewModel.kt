package com.app.fitspace.presentation.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.app.fitspace.FitSpaceApplication
import com.app.fitspace.data.local.ProfilePicture
import com.app.fitspace.data.local.ProfilePictureDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfilePictureViewModel (val profileDao:ProfilePictureDao):ViewModel() {



    fun insertPicture(picture: ProfilePicture) {
        viewModelScope.launch {
            profileDao.insertProfilePicture(picture)
        }
    }

    fun getPicture(): LiveData<ProfilePicture> {
        return profileDao.getPicture()
    }



    fun getRoundedBitmap(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(0, 0, width, height)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }



    fun setProfileImage(imageView: ImageView) {
        getPicture().observeForever { userProfile ->
            if (userProfile != null) {
                val bitmap = BitmapFactory.decodeByteArray(
                    userProfile.profilePicture,
                    0,
                    userProfile.profilePicture.size
                )

                val resizedBitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    userProfile.width,
                    userProfile.height,
                    false
                )

                val roundedBitmap = getRoundedBitmap(resizedBitmap)

                imageView.setImageBitmap(roundedBitmap)

            }
        }
    }


    companion object {
        fun getVmFactory(application: Application): ViewModelProvider.Factory {
            val dataBaseInstance = (application as FitSpaceApplication).getAppDataBase()
            val dao  = dataBaseInstance.ProfilePictureDao()

            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return ProfilePictureViewModel(dao) as T
                }
            }
        }
    }

}




