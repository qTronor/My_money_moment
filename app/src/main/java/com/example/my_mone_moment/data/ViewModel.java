package com.example.my_mone_moment.data;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModel extends AndroidViewModel {

    private OperationRepository operationRepository;
    private LiveData<List<Operation>> allOperationsList;
    private LiveData<List<Operation>> allOperationsListExpense;
    private LiveData<List<Operation>> allOperationsListIncome;

    public ViewModel(Application application){
        super(application);
        operationRepository = new OperationRepository(application);
        allOperationsList = operationRepository.getAllOperations();
        allOperationsListExpense = operationRepository.getAllOperationsExpense();
        allOperationsListIncome = operationRepository.getAllOperationsIncome();

    }

    public void insert(Operation operation) { operationRepository.insert(operation); }

    public void delete(Operation operation) {operationRepository.delete(operation);}

    public void deleteAll() {operationRepository.deleteAll();}

    public LiveData<List<Operation>> getAllOperations() { return allOperationsList; }

    public LiveData<List<Operation>> getAllOperationsExpense() { return allOperationsListExpense; }
    public LiveData<List<Operation>> getAllOperationsIncome() { return allOperationsListIncome; }
    public LiveData<Integer> getExpenseSum() { return operationRepository.getSumExpense();}

}
