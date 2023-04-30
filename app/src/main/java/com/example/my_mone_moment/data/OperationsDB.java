package com.example.my_mone_moment.data;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Operation.class}, version = 1, exportSchema = true)
public abstract class OperationsDB extends RoomDatabase {

    public abstract OpDao opDao();

    private static  volatile OperationsDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static OperationsDB getOperationsDB(final Context context){
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
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                OpDao dao = INSTANCE.opDao();
                dao.deleteAll();

                Operation operation = new Operation("Gym", "2000", "12/12/2023", true);
                dao.insert(operation);
                operation = new Operation("Grocery", "150", "15/12/2023", true);
                dao.insert(operation);
            });
        }
    };

}

