package com.example.my_mone_moment.data;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModel extends AndroidViewModel {

    private OperationRepository operationRepository;
    private LiveData<List<Operation>> allOperationsList;

    public ViewModel(Application application){
        super(application);
        OpDao opDao = OperationsDB.getDatabase(application).opDao();
        operationRepository = new OperationRepository(opDao);
        this.allOperationsList = operationRepository.getAllOperations();
    }

    public void insert(Operation operation) { operationRepository.insert(operation); }

    public LiveData<List<Operation>> getAllOperations() { return allOperationsList; }

}
