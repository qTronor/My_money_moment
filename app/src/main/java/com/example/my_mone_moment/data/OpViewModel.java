package com.example.my_mone_moment.data;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class OpViewModel extends AndroidViewModel {

    private OperationRepository operationRepository;

    private final LiveData<List<Operation>> allOperations;


    public OpViewModel(Application application) {
        super(application);

        //allOperations = OperationsDB.getOperationsDB(getApplication()).opDao().getAll();
        this.operationRepository = new OperationRepository(getApplication());
        this.allOperations = operationRepository.getAllOperations();
    }

    public LiveData<List<Operation>> getAllOperations() { return allOperations; }

    public void insert(Operation operation) { operationRepository.insert(operation); }

}
