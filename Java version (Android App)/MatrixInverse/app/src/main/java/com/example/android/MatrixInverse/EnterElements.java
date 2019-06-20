package com.example.android.MatrixInverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

/*

@author: Asmaa Mirkhan / 2017

*/



public class EnterElements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_elements);
        Intent intent = getIntent();
        final int dimension = intent.getIntExtra("dim",0);
        final  int opt = intent.getIntExtra("option",0);
        final EditText[][] mat = new EditText[dimension][dimension];
        GridLayout glayout = (GridLayout)findViewById(R.id.gridLayout);
        glayout.setRowCount(dimension);
        glayout.setColumnCount(dimension);
        if(opt==2) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    mat[i][j] = new EditText(this);
                    mat[i][j].setHint(" A(" + (i + 1) + ", " + (j + 1) + ") ");
                    setPos(mat[i][j], i, j);
                    glayout.addView(mat[i][j]);
                }
            }
        } else{
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    mat[i][j] = new EditText(this);
                    int val = (int)(Math.random()*(-100)+50);
                    mat[i][j].setText(String.valueOf(val));
                    setPos(mat[i][j], i, j);
                    glayout.addView(mat[i][j]);
                }
            }
        }
        Button but = (Button) findViewById(R.id.hesapla);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean invalid = false;
                String [][]matrix = new String[dimension][dimension];
                for(int i=0; i<dimension; i++){
                    for (int j=0; j<dimension; j++){
                        matrix[i][j]=mat[i][j].getText().toString();
                        if(!isDouble(matrix[i][j]))
                            invalid=true;
                    }
                }
                if(!invalid) {
                    Intent myInt = new Intent(EnterElements.this, ResultActivity.class);
                    Bundle bun = new Bundle();
                    bun.putSerializable("myMat", matrix);
                    myInt.putExtras(bun);
                    startActivity(myInt);
                }else{
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.an_invalid_val),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setPos(EditText edit, int row, int col){
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.width=200;
        param.height=GridLayout.LayoutParams.WRAP_CONTENT;
        param.setGravity(Gravity.CENTER);
        param.rowSpec=GridLayout.spec(row);
        param.columnSpec=GridLayout.spec(col);
        param.setMargins(3,3,3,3);
        edit.setLayoutParams(param);
    }
    public boolean isDouble(String str){
        if(str.isEmpty())
            return false;
        if(str.equals(".")||str.charAt(str.length()-1)=='.')
            return false;
        for(int i=0; i<str.length(); i++){
            if( !Character.isDigit(str.charAt(i)) && str.charAt(i)!='.'&&str.charAt(i)!='-' )
                return false;
        }
        if(str.charAt(0)=='-' && Character.isDigit(str.charAt(1))){
            return true;
        }
        return true;
    }
}
