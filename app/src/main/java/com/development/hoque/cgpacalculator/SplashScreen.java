package com.development.hoque.cgpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(2000);
                    Intent intent =new Intent(getApplicationContext(), Main_menu.class);
                    startActivity(intent);
                    finish();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent intent =new Intent(getApplicationContext(), Main_menu.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        myThread.start();
    }
}
