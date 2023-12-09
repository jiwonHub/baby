package com.example.baby.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FriendsDao {

    @Query("SELECT * FROM friends")
    List<FriendsEntity> getAll();

    @Insert
    void insert(FriendsEntity friend);

}
