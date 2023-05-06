package com.example.my_mone_moment.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OperationRepository {

    private OpDao opDao;
    private LiveData<List<Operation>> allOperations;


    OperationRepository(Application application){
        OperationsDB db = OperationsDB.getDatabase(application);
        opDao = db.opDao();
        allOperations = opDao.getAll();
    }

    public void insert (Operation operation) {
        new insertAsyncTask(opDao).execute(operation);
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(opDao).execute();
    }
    void update(Operation operation){
        opDao.insert(operation);
    }
    void delete(Operation operation){
        new deleteOperationAsyncTask(opDao).execute(operation);
    }

    LiveData<Integer> getSumExpense(){ return opDao.getSumExpense(); }
    LiveData<List<Operation>> getAllOperations(){
        return opDao.getAll();
    }
    LiveData<List<Operation>> getAllOperationsExpense(){
        return opDao.getAllExpense();
    }
    LiveData<List<Operation>> getAllOperationsIncome(){
        return opDao.getAllIncome();
    }

    private static class insertAsyncTask extends AsyncTask<Operation, Void, Void> {
        private OpDao mAsyncTaskDao;

        insertAsyncTask(OpDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(final Operation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private OpDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(OpDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteOperationAsyncTask extends AsyncTask<Operation, Void, Void> {
        private OpDao mAsyncTaskDao;

        deleteOperationAsyncTask(OpDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Operation... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
