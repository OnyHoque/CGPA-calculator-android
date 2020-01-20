package com.development.hoque.cgpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Repeat_menu extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button calculate;
    TextView text;

    double prev_CGPA,total_Credit,old_G,new_G,course_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        e1 = (EditText)findViewById(R.id.editText2);
        e2 = (EditText)findViewById(R.id.editText3);
        e3 = (EditText)findViewById(R.id.editText4);
        e4 = (EditText)findViewById(R.id.editText5);
        e5 = (EditText)findViewById(R.id.editText6);
        text = (TextView) findViewById(R.id.textView14);
        calculate = (Button)findViewById(R.id.button2);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getvalues();
            }
        });
    }

    void Getvalues()
    {
        int n = 0;
        try
        {
            prev_CGPA = Double.parseDouble(e1.getText().toString());
            n = 1;
            total_Credit = Double.parseDouble(e2.getText().toString());
            n = 2;
            old_G = Double.parseDouble(e3.getText().toString());
            n = 3;
            new_G = Double.parseDouble(e4.getText().toString());
            n = 4;
            course_C = Double.parseDouble(e5.getText().toString());

            CalculateGrade();
        }catch (Exception e)
        {
            switch (n) {
                case 0:
                    Toast.makeText(getApplicationContext(),"Invalid previous CGPA!",Toast.LENGTH_SHORT).show();
                case 1:
                    Toast.makeText(getApplicationContext(),"Invalid credit completed!",Toast.LENGTH_SHORT).show();
                case 2:
                    Toast.makeText(getApplicationContext(),"Invalid old Grade value!",Toast.LENGTH_SHORT).show();
                case 3:
                    Toast.makeText(getApplicationContext(),"Invalid new Grade value!",Toast.LENGTH_SHORT).show();
                case 4:
                    Toast.makeText(getApplicationContext(),"Invalid retaken/repeated course credit value!",Toast.LENGTH_SHORT).show();
                default:
                    return;
            }
        }
    }

    void CalculateGrade()
    {
        double value = (prev_CGPA*total_Credit)-(course_C*old_G);
        value = value+(new_G*course_C);
        value /= total_Credit;

        text.setText(""+value);
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
