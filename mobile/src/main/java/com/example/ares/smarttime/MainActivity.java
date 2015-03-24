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

    private char octal[]={'0','1','2','3','4','5','6','7'};
    private char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
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
                                TextView number = (TextView)findViewById(R.id.clockNumberText);
                                TextView bin = (TextView)findViewById(R.id.clockBinText);
                                TextView octa = (TextView)findViewById(R.id.clockOctaText);
                                TextView hex = (TextView)findViewById(R.id.clockHexText);

                                int minutes =  (int)((current.getTimeInMillis()/(1000*60))% 60);

                                number.setText(String.valueOf(minutes));
                                bin.setText(transformToBinary(minutes));
                                octa.setText(transformToOctal(minutes));
                                hex.setText(transformToHex(minutes));

                            }
                        });
                    }
                }catch (InterruptedException e){
                }
            }
        };
        t.start();
    }

    private String transformToBinary(int value){
        if (value == 0){
            return "0";
        }
        if (value == 1){
            return "1";
        }
        if(value %2 == 0 ){
            return transformToBinary(value/2)+"0";
        }
        else{
            return transformToBinary(value/2)+"1";
        }
    }
    private String transformToOctal(int value ){
        String ret ="";
        while(value >0){
            int tmp = value %8;
            ret = octal[tmp]+ret;
            value = value /8;
        }
        return ret;
    }
    private String transformToHex(int value){
        String ret="";
        while(value >0){
            int tmp = value %16;
            ret = hex[tmp]+ret;
            value = value /16;
        }
        return ret;
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
