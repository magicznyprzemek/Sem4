package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public EditText edit_name;
    public EditText edit_surname;
    public EditText edit_pesel;
    public EditText edit_age;
    public EditText edit_gender;

    public ListView lvWyniki;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    Dane dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record);
        edit_name = (EditText) findViewById(R.id.name_input);
        edit_surname = (EditText) findViewById(R.id.surname_input);
        edit_pesel = (EditText) findViewById(R.id.pesel_input);
        edit_age = (EditText) findViewById(R.id.age_input);
        edit_gender = (EditText) findViewById(R.id.gender_input);


        dm = new Dane(this);


    }
    /*
    public void viewData(View v)
    {
        lvWyniki = findViewById(R.id.lista);
        final SimpleCursorAdapter simpleCursorAdapter = Dane.populateListViewFromDB();
        lvWyniki.setAdapter(simpleCursorAdapter);
        lvWyniki.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(1);
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
            }
        });
    }
 */


    public void OpenNewRecord(View v) {
        setContentView(R.layout.add_record);
    }

    public void DelRecord(View v) {
        EditText del = (EditText) findViewById(R.id.del_name_input);
        dm.delete(del.getText().toString());
    }

    public void OpenDel(View v) {
        setContentView(R.layout.del_item);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getPesel() {
        String a = edit_pesel.getText().toString();
        int d;
        try {
            d = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            d = getRandomNumber(1000000000, 2100000000);
        }
        return String.valueOf(d);
    }

    public String getAge() {
        String a = edit_age.getText().toString();
        int d;
        try {
            d = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            d = getRandomNumber(1, 120);
        }
        return String.valueOf(d);
    }

    public String getName() {
        String[] imiona = {"Jan", "Antoni", "Brajanek"};
        String a = edit_name.getText().toString();
        if (a.equals("")|| a.equals(null)) {
            a = imiona[getRandomNumber(0, 2)];
        }
        return a;
    }
    public String getSurname() {
        String[] imiona = {"Kowalski", "Macierewicz"};
        String a = edit_surname.getText().toString();
        if (a.equals("")|| a.equals(null)) {
            a = imiona[getRandomNumber(0, 2)];
        }
        return a;
    }
    public String getGender() {
        String a = edit_gender.getText().toString();
        if (a.equals("") || a.equals(null)) {
            a = "nie_sprecyzowano";
        }
        return a;
    }



    public void AddBtn(View v) {
        dm.insert(getPesel(), getName(), getSurname(), getAge(), getGender());
        setContentView(R.layout.activity_main);
    }

    public void CloseAddBtn(View v) {
        setContentView(R.layout.activity_main);
    }


}