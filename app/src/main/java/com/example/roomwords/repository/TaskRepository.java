package com.example.roomwords.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomwords.db.TaskDao;
import com.example.roomwords.db.TaskRoomDatabase;
import com.example.roomwords.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.wordDao();
        mAllTasks = mTaskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public void insert(Task task) {
        new insertAsyncTask(mTaskDao).execute(task);
    }

    public void deleteAll() {
        new deleteAllTasksAsyncTask(mTaskDao).execute();
    }

    public void deleteTask(Task task) {
        new deleteTaskAsyncTask(mTaskDao).execute(task);
    }


    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        public insertAsyncTask(TaskDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mAsyncTaskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class deleteAllTasksAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        public deleteAllTasksAsyncTask(TaskDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        public deleteTaskAsyncTask(TaskDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mAsyncTaskDao.deleteTask(tasks[0]);
            return null;
        }
    }

}
