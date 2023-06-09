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

@Database(entities = {Operation.class}, version = 1, exportSchema = false)
public abstract class OperationsDB extends RoomDatabase {

    private static volatile OperationsDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract OpDao opDao();

    static OperationsDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OperationsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    OperationsDB.class, "operation_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }

        /*@Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                OpDao dao = INSTANCE.opDao();
                dao.deleteAll();

                Operation operation = new Operation("Hello", 222, "12/12/2002", true);
                dao.insert(operation);
                operation = new Operation("World", 111, "30/12/0000", false);
                dao.insert(operation);
            });
        }*/
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final OpDao mDao;
        Operation[] operations = {new Operation("Hello", "222", "12/12/2002", true),
                                new Operation("World", "111", "30/12/0000", false)};

        PopulateDbAsync(OperationsDB db) {
            mDao = db.opDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // If we have no words, then create the initial list of words
            if (mDao.getAnyWord().length < 1) {
                for (int i = 0; i <= operations.length - 1; i++) {
                    mDao.insert(operations[i]);
                }
            }
            return null;
        }
    }

}

