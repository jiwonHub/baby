package com.example.baby.db;

import androidx.room.Database;

@Database(entities = {FriendsEntity.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract FriendsDao friendsDao();

}
