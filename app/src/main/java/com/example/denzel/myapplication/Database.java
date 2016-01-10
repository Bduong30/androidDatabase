package com.example.denzel.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by denzel on 1/9/16.
 */
public class Database extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private static Database sInstance;

    private static final String DATABASE_NAME = "quotesDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUOTES = "quotes";


    private static final String KEY_QUOTE_ID = "id";
    private static final String QUOTE_COLUMN = "quote";




    public static synchronized Database getSingleton(Context context){
        if(sInstance == null){
            sInstance = new Database(context);
        }
        return sInstance;
    }

    private Database (Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_QUOTES_TABLE = "CREATE TABLE " + TABLE_QUOTES +
                "(" +
                    KEY_QUOTE_ID + " INTEGER PRIMARY KEY," +
                    QUOTE_COLUMN + " TEXT " +
                ")";
        db.execSQL(CREATE_QUOTES_TABLE);
        ContentValues values = new ContentValues();
        values.put(KEY_QUOTE_ID, 1);
        values.put(QUOTE_COLUMN, "This is the first quote");
        db.insert(TABLE_QUOTES, null, values);
    }

    public Quotes getOneQuote(int userInput){
        Quotes newQuotes = new Quotes();
        String QUOTES_SELECT_QUERY = "SELECT " + QUOTE_COLUMN + " FROM " +
                    TABLE_QUOTES + " WHERE " + KEY_QUOTE_ID + " = " + userInput;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUOTES_SELECT_QUERY, null);


        try{
            if(cursor.moveToFirst()){
                newQuotes.id = cursor.getInt(cursor.getColumnIndex(KEY_QUOTE_ID));
                newQuotes.quotes = cursor.getString(cursor.getColumnIndex(QUOTE_COLUMN));
            }
        } catch(Exception e){
            Log.d(TAG, "Error");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return newQuotes;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
