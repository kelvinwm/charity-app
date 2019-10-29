package com.example.princekelvin.charity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.princekelvin.charity.Database.CharityDao;
import com.example.princekelvin.charity.Database.CharityDatabase;
import com.example.princekelvin.charity.Model.CharityModel;

import java.util.List;


public class Repository {
    private LiveData<List<CharityModel>> allCharities;
    private CharityDatabase charityDatabase;
    private CharityDao charityDao;

    public Repository(Application application) {
        charityDatabase = CharityDatabase.getInstance(application);
        charityDao = charityDatabase.charityDao();
        allCharities = charityDao.getAllCharities();
    }


    public LiveData<List<CharityModel>> getAllCharities() {
        return allCharities;
    }

    public void insertCharity(CharityModel charityModel) {
        new InsertAsyncTask(charityDao).execute(charityModel);
    }

    public void updateCharity(CharityModel charityModel) {
        new UpdateAsyncTask(charityDao).execute(charityModel);
    }


    private static class InsertAsyncTask extends AsyncTask<CharityModel, Void, Void> {
        private CharityDao charityDao;

        public InsertAsyncTask(CharityDao charityDao) {
            this.charityDao = charityDao;
        }

        @Override
        protected Void doInBackground(CharityModel... charityModels) {
            charityDao.insertCharity(charityModels[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<CharityModel, Void, Void> {
        private CharityDao charityDao;

        public UpdateAsyncTask(CharityDao charityDao) {
            this.charityDao = charityDao;
        }

        @Override
        protected Void doInBackground(CharityModel... charityModels) {
            charityDao.updateCharity(charityModels[0]);
            return null;
        }

    }

}