package com.example.roomwords.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwords.model.Task;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDao wordDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    TaskRoomDatabase.class,
                                    "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final TaskDao mTaskDao;

        public PopulateDBAsync(TaskRoomDatabase db) {
            mTaskDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTaskDao.deleteAll();
            return null;
        }
    }
}
