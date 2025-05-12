package com.tekri.grunts.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tekri.grunts.data.ProfileDatabase
import com.tekri.grunts.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tekri.grunts.data.UserDatabase
import com.tekri.grunts.repository.ProfileRepository

class ProfileViewModel(context: Context) : ViewModel() {

    private val localContext = context.applicationContext
    private val profileDao = UserDatabase.getDatabase(context).profileDao()
    private val repository: ProfileRepository =
        ProfileRepository(profileDao)

    val allProfile: LiveData<List<Profile>> = repository.allProfiles

    fun addProfile(
        name: String,
        description: String,
        userId: Int,
        phone: String,
        imageUri: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(imageUri.toUri())
            val newProfile = Profile(
                name = name,
                description = description,
                phone = phone,
                imagePath = savedImagePath,
                userId = userId
            )
            repository.insert(newProfile)
        }
    }

    fun updateProfile(updatedProfile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(updatedProfile)
        }
    }

    suspend fun getProfileByUserId(userId: Int): Profile? {
        return repository.getProfileByUserId(userId)
    }

    fun deleteProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(profile.imagePath)
            repository.deleteProfile(profile)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = localContext.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(localContext.filesDir, fileName)

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

class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
