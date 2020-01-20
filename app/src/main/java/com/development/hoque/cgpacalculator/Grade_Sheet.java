package com.development.hoque.cgpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class Grade_Sheet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade__sheet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //import AppCompact7
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resource_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_option:
                GoTo(1);
                return true;
            case R.id.menu_developer:
                GoTo(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void GoTo(int n)
    {
        if(n == 1)
            startActivity(new Intent(getApplicationContext(), Main_menu.class));
        if(n == 2)
            startActivity(new Intent(getApplicationContext(), Developer_info.class));
    }
}
