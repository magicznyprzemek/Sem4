package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button startButton;
    public Button redButton;
    public Button blueButton;
    public Button greenButton;
    public Button yellowButton;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton= (Button) findViewById(R.id.buttonStart);
        redButton= (Button) findViewById(R.id.redButton);
        blueButton= (Button) findViewById(R.id.blueButton);
        greenButton= (Button) findViewById(R.id.greenButton);
        yellowButton= (Button) findViewById(R.id.yellowButton);
    }

    public void StartGame(View v)
    {

    }
}