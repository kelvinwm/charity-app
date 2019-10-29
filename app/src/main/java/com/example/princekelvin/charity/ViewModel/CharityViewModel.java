package com.example.princekelvin.charity.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.princekelvin.charity.Model.CharityModel;
import com.example.princekelvin.charity.Repository;

import java.util.List;


public class CharityViewModel extends AndroidViewModel {
    private Repository repository;

    public CharityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<CharityModel>> getAllCharities() {
        return repository.getAllCharities();
    }

    public void insertCharityItem(CharityModel charityModel) {
        repository.insertCharity(charityModel);
    }

    public void updateCharityItem(CharityModel charityModel) {
        repository.updateCharity(charityModel);
    }
}