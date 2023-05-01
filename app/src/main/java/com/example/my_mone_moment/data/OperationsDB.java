package com.example.my_mone_moment.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Operation.class}, version = 1)
public abstract class OperationsDB extends RoomDatabase {

    private static volatile OperationsDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract OpDao opDao();

    static synchronized OperationsDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OperationsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    OperationsDB.class, "operation_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

