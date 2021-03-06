package com.gianmoura.booker.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.gianmoura.booker.R;
import com.gianmoura.booker.helper.BackgroundTask;
import com.gianmoura.booker.helper.FragmentCustomModal;
import com.gianmoura.booker.helper.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertActivity extends Activity {
    private FragmentCustomModal customModal = null;
    private ConnectionTask connectionTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);
        verifyConnection();
    }

    @OnClick(R.id.btnRefresh)
    public void refreshAlert(){
        if (connectionTask == null){
            connectionTask = new ConnectionTask(this);
        }
        if (connectionTask.getStatus() == AsyncTask.Status.PENDING){
            connectionTask.execute();
        }
        if (connectionTask.getStatus() == AsyncTask.Status.FINISHED){
            connectionTask = new ConnectionTask(this);
            connectionTask.execute();
        }
    }

    private class ConnectionTask extends BackgroundTask {

        public ConnectionTask(Context context) {
            super(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            verifyConnection();
            super.doInBackground(params);
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }

    private void verifyConnection(){
        if (Utils.isOnline(this)){
            if (connectionTask != null){
                connectionTask.onPostExecute(true);
                startActivity( new Intent( this, MainActivity.class) );
                finish();
            }
        }
    }

}
