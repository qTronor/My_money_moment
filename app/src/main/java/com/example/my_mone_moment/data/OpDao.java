package com.example.my_mone_moment.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OpDao {
    @Query("SELECT * FROM operation_table")
    LiveData<List<Operation>> getAll();

    @Query("SELECT * FROM operation_table WHERE name = :name")
    Operation getById(String name);


    @Query("SELECT * FROM operation_table WHERE expense = :expense")
    Operation getByExpense(String expense);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Operation operation);

    @Update
    void update(Operation operation);

    @Delete
    void delete(Operation operation);
}
