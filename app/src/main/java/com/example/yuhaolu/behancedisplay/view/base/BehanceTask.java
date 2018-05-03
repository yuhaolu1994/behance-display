package com.example.yuhaolu.behancedisplay.view.base;

import android.os.AsyncTask;

public abstract class BehanceTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected abstract Result doJob(Params... params);

    protected abstract void onSuccess(Result result);

    @Override
    protected Result doInBackground(Params... params) {
        return doJob(params);
    }

    @Override
    protected void onPostExecute(Result result) {
        onSuccess(result);
    }

}
