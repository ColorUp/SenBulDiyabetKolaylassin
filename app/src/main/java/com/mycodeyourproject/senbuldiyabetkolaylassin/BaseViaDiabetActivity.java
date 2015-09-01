package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Burak on 12.07.2015.
 */
public class BaseViaDiabetActivity extends ActionBarActivity
{
    ActionBar mActionBar;
    View mCustomView;
    Handler handler = new Handler();
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_with_progressbar, null);
    }

    public void SetProgressBarTextValueAndShow(String string, final Integer progressValue)
    {
        mProgress = (ProgressBar) mCustomView.findViewById(R.id.progressbar);

        new Thread(new Runnable() {

            int i = 0;
            int progressStatus = 0;

            public void run() {
                while (progressStatus < progressValue) {
                    progressStatus += doWork();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(progressStatus);
                            i++;
                        }
                    });
                }
            }
            private int doWork() {

                return i;
            }

        }).start();

        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(string);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
