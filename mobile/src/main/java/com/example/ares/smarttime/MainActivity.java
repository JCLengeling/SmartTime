package com.example.ares.smarttime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity {

    private GregorianCalendar current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Thread t = new Thread(){
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    current = new GregorianCalendar();
                                    setContentView(R.layout.activity_main);
                                    TextView t = (TextView)findViewById(R.id.clockText);

                                    String timeText = String.valueOf(current.getTime());
                                    t.setText(timeText);

                            }
                        });
                    }
                }catch (InterruptedException e){
                }
            }
        };
        t.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
