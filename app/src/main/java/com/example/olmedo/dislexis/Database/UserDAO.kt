package com.example.olmedo.dislexis.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.olmedo.dislexis.Entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User)

   // @Query("SELECT*FROM repos")
   // fun getAllRepos():LiveData<List<GitHubRepo>>


}