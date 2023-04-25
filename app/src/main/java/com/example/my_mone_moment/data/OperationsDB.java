package com.example.my_mone_moment.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Operation.class}, version = 1)
public abstract class OperationsDB extends RoomDatabase {

    private static OperationsDB operationsDB;
    public abstract OpDao opDao();

    public static synchronized OperationsDB getOperationsDB(Context context){
        if(operationsDB == null) {
            operationsDB = Room.databaseBuilder(context.getApplicationContext(), OperationsDB.class, "operation_table")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return operationsDB;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(operationsDB).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(OperationsDB operationsDB) {
            OpDao dao = operationsDB.opDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

