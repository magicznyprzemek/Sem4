package com.example.sudoku10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView[][] TextArray=new TextView[9][9];
    public Sudoku Sudoku1;
    public Button generate_btn;
    public Button check_btn;
    public Button help_btm;


    int x =TextArray.length;
    int y=TextArray[0].length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectArray();
        // btns
        generate_btn=findViewById(R.id.GenerateB);
        check_btn=findViewById(R.id.CheckB);
        help_btm=findViewById(R.id.Help);
        help_btm.setEnabled(false);
        check_btn.setEnabled(false);

    }
    public void HelpButton(View v)
    {
        int a,b;
        boolean endLoop=false;
        for(int i=0; i< x; i++)
        {
            for(int j=0; j<y; j++)
            {

               if(String.valueOf(TextArray[i][j].getText()).compareTo(String.valueOf(Sudoku1.OriginalArray[i][j]))!=0)
                {
                    Toast toast = Toast.makeText(this, String.valueOf(Sudoku1.OriginalArray[i][j]), Toast.LENGTH_SHORT);
                    toast.show();
                    ColorHelp(i, j);
                    endLoop=true;
                    break;
                }
            }
            if(endLoop)
                break;
        }
    }
    private void ColorHelp(int a, int b)
    {
        TextArray[a][b].setBackgroundColor(Color.GREEN);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                TextArray[a][b].setBackgroundColor(Color.WHITE);

            }
        }, 3000);
    }



    public void Fill(View v)
    {
        Sudoku1=new Sudoku(9, 10);
        Sudoku1.fillValues();
        help_btm.setEnabled(true);
        check_btn.setEnabled(true);
        for(int i=0; i< x; i++)
        {
            for(int j=0; j<y; j++)
            {
                if(Sudoku1.mat[i][j]==0) {
                    TextArray[i][j].setText(" ");
                    TextArray[i][j].setEnabled(true);
                }
                else {
                    TextArray[i][j].setText(String.valueOf(Sudoku1.mat[i][j]));
                    TextArray[i][j].setEnabled(false);
                }
            }
        }
    }
    public void ArrayCheck(View v)
    {
        boolean isOk=true;
       // Sudoku1.printSudoku2();
       // System.out.println("--");
        for(int i=0; i< x; i++)
        {
            for(int j=0; j<y; j++)
            {
                String A = String.valueOf(TextArray[i][j].getText());
                String B = String.valueOf(Sudoku1.OriginalArray[i][j]);
                if (!A.equals(B))
                {
                    isOk = false;
                  // System.out.println(A.compareTo(B)+"--- ."+TextArray[i][j].getText()+". - ."+ String.valueOf(Sudoku1.OriginalArray[i][j])+".");
                }
            }
        }
        if(isOk)
            Toast.makeText(this,"ZGADZA SIE",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"NIE ZGADZA SIE",Toast.LENGTH_SHORT).show();
    }
    private void ConnectArray()
    {

        int z=0;
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                int id = this.getResources().getIdentifier("et"+z, "id", this.getPackageName());
                System.out.println(id);
                try {
                    TextArray[i][j]=(TextView) findViewById(id);
                    TextArray[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                    TextArray[i][j].setText(" ");
                    TextArray[i][j].setClickable(false);
                    TextArray[i][j].setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                }
                catch (Exception x) { } // xd
                if(z<81)
                    z++;
            }
        }
    }


}