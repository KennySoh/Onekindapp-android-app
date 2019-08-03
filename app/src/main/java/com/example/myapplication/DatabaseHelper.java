package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;
    private SQLiteDatabase db_read ;
    private SQLiteDatabase db_write;

    private static final String DATABASE_NAME = "seeds.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SEED = "seed_table";
    public static final String COL_ID_S= "_id";// Index Position in Recycler View
    public static final String COL_NAME_S= "name";

    private static final String TABLE_SEED_CREATE=
            "CREATE TABLE "+TABLE_SEED+" ("+
                    COL_ID_S+" INTEGER PRIMARY KEY,"+
                    COL_NAME_S+" TEXT"+
                    ")";

    //One to Many
    public static final String TABLE_ENTRIES = "entries_table";
    public static final String COL_ID_E = "_id";// TimeStamp
    public static final String COL_TITLE_E = "title";
    public static final String COL_BODY_E = "body";
    public static final String COL_SEED_E = "seed_id";

    private static final String TABLE_ENTRIES_CREATE=
            "CREATE TABLE "+TABLE_ENTRIES+" ("+
                    COL_ID_E+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COL_TITLE_E+" TEXT,"+
                    COL_BODY_E+" TEXT,"+
                    COL_SEED_E+" INTEGER,"+
                    " FOREIGN KEY ("+COL_SEED_E+") REFERENCES "+TABLE_SEED+"("+COL_ID_S+") ON DELETE CASCADE"+
                    ")";

    private static final String SQL_DELETE_SEEDS =
            "DROP TABLE IF EXISTS " + TABLE_SEED;


    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        Log.i("Sql", "getInstance Called");
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
            Log.i("Sql", "new Instance Created");
        }
        return sInstance;
    }
    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_SEED_CREATE);
        db.execSQL(TABLE_ENTRIES_CREATE);
        Log.i("Sql", "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        Log.i("Sql"," onUpgrade called");
        db.execSQL(SQL_DELETE_SEEDS);
        onCreate(db);
    }

    public void clearDataBase(){
        db_write.execSQL(SQL_DELETE_SEEDS);
    }
    public void createSeed(int indexPos, String name){
        db_read = sInstance.getReadableDatabase();
        db_write= sInstance.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_ID_S, indexPos);
        values.put(DatabaseHelper.COL_NAME_S, name);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db_write.replace(DatabaseHelper.TABLE_SEED, null, values);
        Log.i("Sql", "Inserting new device row"+String.valueOf(newRowId));
    }

    public ArrayList<String> readSeed(){
        //1) Reading from SQL
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseHelper.COL_NAME_S,
        };


        // How you want the results sorted in the resulting Cursor
        String sortOrder = DatabaseHelper.COL_ID_S + " DESC";

        Cursor cursor = db_read.query(
                DatabaseHelper.TABLE_SEED,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,          // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        // For example, the following shows how to get all the item IDs stored in a cursor and add them to a list:
        ArrayList<String> mTitleSet = new ArrayList<String>();
        while(cursor.moveToNext()) {
            String itemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME_S));

            Log.i("Sql", "Read Seed:"+ itemName);
            mTitleSet.add(itemName);
        }
        cursor.close();
        Log.i("dataaa",String.valueOf(mTitleSet.size()));
        for(int i = 0; i < mTitleSet.size(); i++){
            Log.i("dataaa",mTitleSet.get(i));
        }

        return mTitleSet;
    }

}
