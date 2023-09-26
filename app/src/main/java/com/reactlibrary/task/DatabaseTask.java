package com.reactlibrary.task;

import android.os.AsyncTask;

public class DatabaseTask extends AsyncTask<Void, Void, Void> {
    private TaskListener mDownloadListener;

    public DatabaseTask(TaskListener iDBTaskListener) {
        this.mDownloadListener = iDBTaskListener;
    }

    public void onPreExecute() {
        TaskListener taskListener = this.mDownloadListener;
        if (taskListener != null) {
            taskListener.onPreExecuteTask();
        }
    }

    public Void doInBackground(Void... voids) {
        TaskListener taskListener = this.mDownloadListener;
        if (taskListener == null) {
            return null;
        }
        taskListener.onDoInBackgroundTask();
        return null;
    }

    public void onPostExecute(Void result) {
        TaskListener mDownloadListener1 = this.mDownloadListener;
        if (mDownloadListener1 != null) {
            mDownloadListener1.onPostExecuteTask();
        }
    }
}
