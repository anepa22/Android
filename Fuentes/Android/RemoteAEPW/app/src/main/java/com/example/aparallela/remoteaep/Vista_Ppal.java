package com.example.aparallela.remoteaep;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Vista_Ppal extends AppCompatActivity implements View.OnTouchListener {
    private ProgressBar pgrGen = null;
    private Handler mHandler;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        ((Button)findViewById(R.id.btn1)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn2)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn3)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn4)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn5)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn6)).setOnTouchListener(this);
        ((Button)findViewById(R.id.btn7)).setOnTouchListener(this);
    }

    @Override public boolean onTouch (View v, MotionEvent event){
    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:

            if(v.getId() == R.id.btn1){
                pgrGen = (ProgressBar)findViewById(R.id.pgr1) ;
            }
            else if (v.getId() == R.id.btn2) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr2) ;
            }
            else if (v.getId() == R.id.btn3) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr3) ;
            }
            else if (v.getId() == R.id.btn4) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr4) ;
            }
            else if (v.getId() == R.id.btn5) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr5) ;
            }
            else if (v.getId() == R.id.btn6) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr6) ;
            }
            else if (v.getId() == R.id.btn7) {
                pgrGen = (ProgressBar)findViewById(R.id.pgr7) ;
            }

            if (mHandler != null) return true;
            mHandler = new Handler();
            mHandler.postDelayed(mAction, 500);
            break;

        case MotionEvent.ACTION_UP:
            if (mHandler == null) return true;
            mHandler.removeCallbacks(mAction);
            mHandler = null;
            pgrGen.setProgress(0);
            i = 0;
            break;
        }
        return true;
    }

    Runnable mAction = new Runnable() {
        @Override public void run() {
            pgrGen.setProgress(i=i+10);
            mHandler.postDelayed(this, 200);

            if (i>=100){

            }
        }
    };
}