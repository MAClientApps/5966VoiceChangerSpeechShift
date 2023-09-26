package com.reactlibrary.task;

public interface TaskListener {
    void onDoInBackgroundTask();

    void onPostExecuteTask();

    void onPreExecuteTask();
}
