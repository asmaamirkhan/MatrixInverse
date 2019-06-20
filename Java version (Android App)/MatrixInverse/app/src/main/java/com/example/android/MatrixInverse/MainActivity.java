package com.example.android.MatrixInverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/*

@author: Asmaa Mirkhan / 2017

*/



public class MainActivity extends AppCompatActivity {
    public int option=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gen_but = findViewById(R.id.generate_but);
        //final int option=0;
        gen_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option=1;
                EditText et = (EditText)findViewById(R.id.mat_dim);
                if(isInteger(et.getText().toString())) {
                    int dim = Integer.parseInt(et.getText().toString());
                    Intent myIntent = new Intent(MainActivity.this, EnterElements.class);
                    myIntent.putExtra("dim", dim);
                    myIntent.putExtra("option", option);
                    startActivity(myIntent);
                }else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_val),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button set_but = findViewById(R.id.set_mat_but);
        set_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option =2;
                EditText et = (EditText)findViewById(R.id.mat_dim);

                if(isInteger(et.getText().toString())) {
                    int dim = Integer.parseInt(et.getText().toString());
                    Intent myIntent = new Intent(MainActivity.this, EnterElements.class);
                    myIntent.putExtra("dim", dim);
                    myIntent.putExtra("option", option);
                    startActivity(myIntent);
                }else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_val),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void calculate (View view){
        EditText et = (EditText)findViewById(R.id.mat_dim);

        if(isInteger(et.getText().toString())) {
            int dim = Integer.parseInt(et.getText().toString());
            Intent myIntent = new Intent(MainActivity.this, EnterElements.class);
            myIntent.putExtra("dim", dim);
            startActivity(myIntent);
        }else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_val),
                    Toast.LENGTH_SHORT).show();
        }
    }*/
    public boolean isInteger(String str){
        if(str.isEmpty())
            return false;
        for(int i=0; i<str.length(); i++){
            if( !Character.isDigit(str.charAt(i)) )
                return false;
        }
        if(Integer.parseInt(str)<1)
            return false;
        return true;
    }
}