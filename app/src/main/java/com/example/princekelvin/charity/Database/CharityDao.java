package com.example.princekelvin.charity.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.princekelvin.charity.Model.CharityModel;

import java.util.List;


@Dao
public interface CharityDao {
    @Query("SELECT * FROM charity_table")
    LiveData<List<CharityModel>> getAllCharities();

    @Insert
    void insertCharity(CharityModel charityModel);

    @Update
    void updateCharity(CharityModel charityModel);

    @Delete
    void deleteCharity(CharityModel charityModel);
}
