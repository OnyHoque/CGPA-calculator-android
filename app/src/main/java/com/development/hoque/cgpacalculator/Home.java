package com.development.hoque.cgpacalculator;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView main_text;
    EditText oldcgpa,oldcredit,newgpa,newcredit;
    Button b,b2,b3;

    double old_CGPA,old_Credit,new_GPA,new_Credit;
    boolean check = true;
    boolean fin = false;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //import AppCompact7
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        main_text = (TextView)findViewById(R.id.textView);
        oldcgpa = (EditText)findViewById(R.id.edit_cgpa);
        oldcredit = (EditText)findViewById(R.id.edit_credit);
        newgpa = (EditText)findViewById(R.id.edit_current_gpa);
        newcredit = (EditText)findViewById(R.id.edit_current_credit);

        final SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.contains("FirstTime")){
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialogbox);
            dialog.setTitle("Tutorial");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText("Please enter courses one at a time. After entering each course press \"+1 course\", then enter another course GPA and credit. When you are done, press Calculate for final result");
            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            checkBox = (CheckBox) dialog.findViewById(R.id.checkBox);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("FirstTime", true);
                        editor.apply();
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        b = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.b_course);
        b3 = (Button)findViewById(R.id.b_reset);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fin = true;
                GetValues();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetValues();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_text.setText("0.0");
                oldcgpa.setText("");
                oldcredit.setText("");
                newgpa.setText("");
                newcredit.setText("");
            }
        });
    }

    private void GetValues()
    {
        try
        {
            old_CGPA = Double.parseDouble(oldcgpa.getText().toString());
        }catch (Exception e)
        {
            check = false;
            oldcgpa.setText("");
            Toast.makeText(this, "Invalid CGPA value!", Toast.LENGTH_SHORT).show();
        }

        try
        {
            old_Credit = Double.parseDouble(oldcredit.getText().toString());
        }catch (Exception e)
        {
            check = false;
            oldcredit.setText("");
            if(!check) {
                Toast.makeText(this, "Invalid credit value!", Toast.LENGTH_SHORT).show();
            }
        }

        try
        {
            new_GPA = Double.parseDouble(newgpa.getText().toString());
        }catch (Exception e)
        {
            check = false;
            newgpa.setText("");
            if(!check) {
                Toast.makeText(this, "Invalid current GPA!", Toast.LENGTH_SHORT).show();
            }
        }

        try
        {
            new_Credit = Double.parseDouble(newcredit.getText().toString());
        }catch (Exception e)
        {
            check = false;
            newcredit.setText("");
            if(!check) {
                Toast.makeText(this, "Invalid current credit!", Toast.LENGTH_SHORT).show();
            }
        }

        if(check)
        {
            calculateCGPA();
        }
        else
        {
            check = true;
        }
    }

    private void calculateCGPA()
    {
        double value = (old_CGPA*old_Credit)+(new_GPA*new_Credit);
        value = value/ (old_Credit+new_Credit);

        if(value >= 0 && value <= 4) {
            if (fin) {
                main_text.setText(String.format("%.2f", value));
                oldcgpa.setText("");
                oldcredit.setText("");
                newgpa.setText("");
                newcredit.setText("");
                fin = false;

            } else {
                main_text.setText(String.format("%.2f", value));
                oldcgpa.setText(String.format("%.2f", value));
                oldcredit.setText("" + (old_Credit + new_Credit));
                newgpa.setText("");
                newcredit.setText("");
            }
        }
        else
        {
            main_text.setText(""+value);
            Toast.makeText(this, "Sorry, my calculation was wrong or you have enter wrong information!", Toast.LENGTH_LONG).show();
        }
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
