package com.example.sudoku10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    TextView[][] TextArray=new TextView[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectArray();
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
                    TextArray[i][i]=(TextView) findViewById(id);
                    TextArray[i][i].setText("1");
                    TextArray[i][i].setInputType(InputType.TYPE_CLASS_NUMBER);
                    TextArray[i][i].setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});


                }
                catch (Exception x) { } // xd
                if(z<81)
                    z++;
            }
        }
    }


}