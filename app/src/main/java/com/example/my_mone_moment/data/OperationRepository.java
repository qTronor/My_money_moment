package com.example.my_mone_moment.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OperationRepository {

    private OpDao opDao;

    OperationRepository(OpDao opDao){
        this.opDao = opDao;
    }

    void insert(Operation operation){
        opDao.insert(operation);
    }

    void update(Operation operation){
        opDao.insert(operation);
    }

    void delete(Operation operation){
        opDao.delete(operation);
    }

    LiveData<List<Operation>> getAllOperations(){
        return opDao.getAll();
    }
}
