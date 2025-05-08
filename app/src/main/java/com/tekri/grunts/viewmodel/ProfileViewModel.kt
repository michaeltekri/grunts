package com.tekri.grunts.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tekri.grunts.data.ProfileDao
import com.tekri.grunts.data.ProfileDatabase
import com.tekri.grunts.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val profileDao = ProfileDatabase.getDatabase(app).profileDao()

    val allProfile: LiveData<List<Profile>> = profileDao.getAllProfiles()

    fun addProfile(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newProfile = Profile(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            profileDao.insertProfile(newProfile)
        }
    }

    fun updateProfile(updatedProfile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileDao.updateProfile(updatedProfile)
        }
    }

    fun deleteProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(profile.imagePath)
            profileDao.deleteProfile(profile)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}