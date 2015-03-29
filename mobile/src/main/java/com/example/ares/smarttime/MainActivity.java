package com.example.ares.smarttime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {

    private boolean decimalMode, binaryMode, octalMode, hexMode;

    private char octal[]={'0','1','2','3','4','5','6','7'};
    private char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private GregorianCalendar current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalMode = true;
        binaryMode = octalMode = hexMode = false;
        setContentView(R.layout.activity_decimal);

        Thread t = new Thread(){
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TimeZone tz = TimeZone.getDefault();

                                current = new GregorianCalendar(tz);


                                int hours   =   current.get(Calendar.HOUR_OF_DAY);
                                int minutes =   current.get(Calendar.MINUTE);
                                int second  =   current.get(Calendar.SECOND);


                                if(decimalMode){
                                    TextView hourTextView = (TextView)findViewById(R.id.clockHour);
                                    TextView minuteTextView = (TextView)findViewById(R.id.clockMinute);
                                    TextView secondTextView = (TextView)findViewById(R.id.clockSecond);


                                    hourTextView.setText(String.valueOf(hours));
                                    minuteTextView.setText(String.valueOf(minutes));
                                    secondTextView.setText(String.valueOf(second));
                                }else if (binaryMode){
                                    TextView hourTextView = (TextView)findViewById(R.id.clockHour);
                                    TextView minuteTextView = (TextView)findViewById(R.id.clockMinute);
                                    TextView secondTextView = (TextView)findViewById(R.id.clockSecond);

                                    hourTextView.setText(transformToBinary(hours));
                                    minuteTextView.setText(transformToBinary(minutes));
                                    secondTextView.setText(transformToBinary(second));
                                }else if (octalMode){
                                    TextView hourTextView = (TextView)findViewById(R.id.clockHour);
                                    TextView minuteTextView = (TextView)findViewById(R.id.clockMinute);
                                    TextView secondTextView = (TextView)findViewById(R.id.clockSecond);

                                    hourTextView.setText(transformToOctal(hours));
                                    minuteTextView.setText(transformToOctal(minutes));
                                    secondTextView.setText(transformToOctal(second));
                                }else if (hexMode){
                                    TextView hourTextView = (TextView)findViewById(R.id.clockHour);
                                    TextView minuteTextView = (TextView)findViewById(R.id.clockMinute);
                                    TextView secondTextView = (TextView)findViewById(R.id.clockSecond);

                                    hourTextView.setText(transformToHex(hours));
                                    minuteTextView.setText(transformToHex(minutes));
                                    secondTextView.setText(transformToHex(second));
                                }
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
        if (value == 0)
            ret = "0";
        while(value >0){
            int tmp = value %8;
            ret = octal[tmp]+ret;
            value = value /8;
        }
        return ret;
    }
    private String transformToHex(int value){
        String ret="";
        if (value == 0)
            ret = "0";
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
        if (id == R.id.action_decimal) {
            setContentView(R.layout.activity_decimal);
            decimalMode = true;
            binaryMode = octalMode = hexMode = false;
            return true;
        }else if (id == R.id.action_binary) {
            setContentView(R.layout.activity_binary);
            binaryMode = true;
            decimalMode = octalMode = hexMode = false;
            return true;
        }else if (id == R.id.action_octal) {
            setContentView(R.layout.activity_octal);
            octalMode = true;
            decimalMode = binaryMode = hexMode = false;
            return true;
        }else if (id == R.id.action_hex) {
            setContentView(R.layout.activity_hex);
            hexMode = true;
            decimalMode = octalMode = binaryMode = false;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
