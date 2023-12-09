package com.example.baby.db;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInitializer {

    private static RoomDatabase roomDatabase;

    public static RoomDatabase initDatabase(Context context){
        if (roomDatabase == null){
            roomDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RoomDatabase.class,
                    "room-database"
            )
                    .allowMainThreadQueries()
                    .build();
        }
        return roomDatabase;
    }

}
