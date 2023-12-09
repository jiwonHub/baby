package com.example.baby.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "friends")
public class FriendsEntity {

    @PrimaryKey (autoGenerate = true)
    public int id;

    public String name;

    public FriendsEntity(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass()!=obj.getClass()) return false;
        FriendsEntity that = (FriendsEntity) obj;
        return Objects.equals(name, that.name);
    }

    public int hashCode(){
        return Objects.hash(name);
    }
}
