package com.tekri.grunts.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tekri.grunts.model.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getAllProfiles(): LiveData<List<Profile>>

    @Insert
    suspend fun insertProfile(profile: Profile)

    @Update
    suspend fun updateProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)
}