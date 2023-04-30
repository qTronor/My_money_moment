package com.example.my_mone_moment.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

public class OperationRepository {

    private OpDao opDao;
    public LiveData<List<Operation>> listLiveData;

    public OperationRepository(Application application){
        OperationsDB db = OperationsDB.getOperationsDB(application);
        opDao = db.opDao();
        listLiveData = opDao.getAll();
    }

    LiveData<List<Operation>> getAllOperations(){
        return listLiveData;
    }

    void insert(Operation operation){
        OperationsDB.databaseWriteExecutor.execute(() -> {opDao.insert(operation);});
    }
}
