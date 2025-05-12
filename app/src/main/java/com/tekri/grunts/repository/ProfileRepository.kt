package com.tekri.grunts.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.tekri.grunts.data.ProfileDao
import com.tekri.grunts.model.Profile

class ProfileRepository(private val profileDao: ProfileDao) {
    val allProfiles: LiveData<List<Profile>> = profileDao.getAllProfiles()

    suspend fun insert(profile: Profile) {
        profileDao.insertProfile(profile)
    }

    suspend fun update(profile: Profile) {
        profileDao.updateProfile(profile)
    }

    suspend fun deleteProfile(profile: Profile) {
        profileDao.deleteProfile(profile)
    }

    suspend fun getProfileByUserId(userId: Int): Profile? {
        return profileDao.getProfileByUserId(userId)
    }
}