package com.example.android.MatrixInverse;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/*

@author: Asmaa Mirkhan / 2017

*/

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String[][] recieve = null;
        Object[] obArray = (Object[])getIntent().getExtras().getSerializable("myMat");
        if(obArray!=null){
            recieve =new String[obArray.length][];
            for(int i = 0; i < obArray.length; i++){
                recieve[i] = (String[])obArray[i];
            }
        }
        int dim = obArray.length,i,j,k;
        double div;
        double matrix [][] = new double[dim][dim*2];
        boolean sing = false;
        for(i = 0; i < dim; i++){
            for(j = 0; j < dim; j++) {
                matrix[i][j] = Double.parseDouble(recieve[i][j]);
            }
        }
        for(i = 0; i < dim; i++){
            for(j = dim; j < dim * 2; j++) {
                if(j == i + dim)
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = 0;
                System.out.print(matrix[i][j]);
            }
        }

        for(j = 0; j < dim; j++) {
            pivotlama(matrix, dim, dim * 2, j);
            for (i = j; i < dim; i++) {
                div = matrix[i][j];
                for (k = 0; k < dim * 2; k++) {
                    if (div != 0)
                        matrix[i][k] = matrix[i][k] / div;
                    if (i != j && div != 0)
                        matrix[i][k] = matrix[i][k] - matrix[j][k];
                }

                if (isSingular(matrix, dim)) {
                    sing = true;
                    break;
                }
            }
        }
            if (!sing) {
                for (j = dim - 1; j >= 0; j--) {
                    for (i = j; i >= 0; i--) {
                        div = matrix[i][j];
                        for (k = 0; k < dim * 2; k++) {
                            if (div != 0)
                                matrix[i][k] = matrix[i][k] / div;
                            if (i != j && div != 0)
                                matrix[i][k] = matrix[i][k] - matrix[j][k];
                        }
                        if (isSingular(matrix, dim)) {
                            sing = true;
                            break;
                        }
                    }
                }
                if(!sing) {
                    double inv[][] = new double[dim][dim];
                    for (i = 0; i < dim; i++) {
                        for (j = 0; j < dim; j++) {
                            inv[i][j] = matrix[i][j + dim];
                        }
                    }

                    TextView[][] results = new TextView[dim][dim];
                    String str = "";
                    GridLayout glayout = (GridLayout) findViewById(R.id.resultGridLayout);
                    glayout.setColumnCount(dim);
                    glayout.setRowCount(dim);
                    for (i = 0; i < dim; i++) {
                        for (j = 0; j < dim; j++) {
                            results[i][j] = new TextView(this);
                            results[i][j].setText(String.format("%.4f", matrix[i][j + dim]));
                            setPos(results[i][j], i, j);
                            glayout.addView(results[i][j]);
                        }
                    }
                }
            }if(sing){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.unique_mat),
                        Toast.LENGTH_SHORT).show();
                TextView tv = findViewById(R.id.result_is);
                tv.setText(R.string.unique_mat);

            }
                Button new_m = findViewById(R.id.new_matrix);
                new_m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(ResultActivity.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                });

        }

    private void setPos(TextView textV, int row, int col){
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.width = 200;
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(col);
        param.setMargins(4, 4, 4, 4);
        textV.setTextSize(16);
        textV.setPadding(8, 8, 8, 8);
        textV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textV.setTextColor(getResources().getColor(R.color.colorText));
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getResources().getColor(R.color.colorLightPrimary));
        gd.setCornerRadius(5);
        textV.setBackground(gd);
        textV.setLayoutParams(param);
    }
    public void pivotlama(double matris[][], int m, int n, int satir) {
        double max, temp;
        int index = satir, i;
        max = matris[satir][satir];
        for(i = satir; i<m; i++) {
            if(Math.abs(matris[i][satir]) > Math.abs(max)) {
                max = matris[i][satir];
                index = i;
            }
        }
        for(i = 0; i < n; i++) {
            temp = matris[satir][i];
            matris[satir][i] = matris[index][i];
            matris[index][i] = temp;
        }
    }
    static boolean isSingular(double matris[][], int m) {
        int zeros = 0;
        int sifir = 0;
        double bir, iki;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                bir = Math.round(matris[i][j] * 10000);
                bir /= 10000;
                if(bir == 0) {
                    zeros++;
                }
                iki = Math.round(matris[j][i] * 10000);
                iki /= 10000;
                if(iki == 0) {
                    sifir++;
                }
            }
            if(zeros == m || sifir == m) {
                return true;
            }
            zeros = 0;
            sifir = 0;
        }
        return false;
    }
}
