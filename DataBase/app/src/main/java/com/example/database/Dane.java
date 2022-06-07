package com.example.database;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.util.Log;



class Dane {
    DatabaseHelper helper;
    SQLiteDatabase db;
    Context context;

    public Dane(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public SimpleCursorAdapter populateListViewFromDB(){
        String columns[] = {DatabaseHelper.TABLE_ROW_ID, DatabaseHelper.DB_NAME, DatabaseHelper.TABLE_ROW_SURNAME};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        String[] fromFieldNames = new String[]{
                DatabaseHelper.TABLE_ROW_ID, DatabaseHelper.TABLE_NAME, DatabaseHelper.TABLE_ROW_SURNAME
        };
        int[] toViewIDs = new int[]{R.id.item_id, R.id.item_name, R.id.item_test_text};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,
                R.layout.item,
                cursor,
                fromFieldNames,
                toViewIDs
        );
        return contactAdapter;
    }









    private class DatabaseHelper extends SQLiteOpenHelper {


        public static final String TABLE_ROW_ID = "_id";
        public static final String TABLE_ROW_PESEL = "pesel";
        public static final String TABLE_ROW_NAME = "name";
        public static final String TABLE_ROW_SURNAME = "surname";
        public static final String TABLE_ROW_AGE = "age";
        public static final String TABLE_ROW_GENDER = "gender";


        private static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "BAZA";
        public static final String DB_NAME = "baz.db";

        public Cursor viewData() {
            SQLiteDatabase db2 = this.getReadableDatabase();
            String query = "Select * from " + TABLE_NAME;
            Cursor cursor = db2.rawQuery(query, null);
            return cursor;
        }

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString = "CREATE TABLE " + TABLE_NAME + " ("
                    + TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TABLE_ROW_PESEL + " TEXT,"
                    + TABLE_ROW_NAME + " TEXT,"
                    + TABLE_ROW_SURNAME + " TEXT,"
                    + TABLE_ROW_AGE + " TEXT,"
                    + TABLE_ROW_GENDER + " TEXT)";

            db.execSQL(newTableQueryString);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // this method is called to check if the table exists already.
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }




    public void insert(String name, String surname, String pesel, String age, String gender) {
        String query = "INSERT INTO " + DatabaseHelper.TABLE_NAME + " (" +
                DatabaseHelper.TABLE_ROW_PESEL + ", " + DatabaseHelper.TABLE_ROW_NAME + ", " + DatabaseHelper.TABLE_ROW_SURNAME + ", " + DatabaseHelper.TABLE_ROW_AGE + ", " + DatabaseHelper.TABLE_ROW_GENDER + ") " +
                "VALUES (" +
                "'" + pesel + "'" + ", " + "'" + name + "'" + ", " + "'" + surname + "'" + ", " + "'" + age + "'" + ", " + "'" + gender + "');";
        Log.i("insert()= ", query);
        db.execSQL(query);
    }

    public void delete(String name) {
        String query = "DELETE FROM " + DatabaseHelper.TABLE_NAME +
                "WHERE " + DatabaseHelper.TABLE_ROW_NAME +
                " = ' " + name + "';";
        Log.i("delete() = ", query);
    }

    public Cursor selectALL() {
        Cursor c = db.rawQuery("SELECT * from " +
                DatabaseHelper.TABLE_ROW_NAME, null);
        return c;
    }

    public Cursor searchName(String name) {
        String query = "SELECT " +
                DatabaseHelper.TABLE_ROW_ID + ", " +
                DatabaseHelper.TABLE_ROW_NAME +
                ", " + DatabaseHelper.TABLE_ROW_SURNAME +
                " FROM " +
                DatabaseHelper.TABLE_ROW_NAME + " WHERE " +
                DatabaseHelper.TABLE_ROW_NAME + " = '" + name + "';";
        Log.i("searchName() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;

    }


}
