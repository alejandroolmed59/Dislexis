package com.example.olmedo.dislexis.Database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.olmedo.dislexis.Database.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

   // @Query("SELECT*FROM repos")
   // fun getAllRepos():LiveData<List<GitHubRepo>>


}