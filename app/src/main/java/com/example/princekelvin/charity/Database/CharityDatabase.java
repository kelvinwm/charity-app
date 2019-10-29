package com.example.princekelvin.charity.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.princekelvin.charity.Model.CharityModel;


@Database(entities = {CharityModel.class}, exportSchema = false, version = 1)
public abstract class CharityDatabase extends RoomDatabase {
    private static final String DB_NAME = "charity_db";
    private static CharityDatabase instance;

    public abstract CharityDao charityDao();

    public static synchronized CharityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CharityDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}