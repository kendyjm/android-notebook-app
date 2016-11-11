package com.kendyjm.notebook;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kendy on 07/11/16.
 * TODO seriously ? this coode is shit, conception 0
 */

public class NotebookDbAdapter {
    public static final String DATABASE_NAME = "notebook.db";
    public static final int DATABASE_VERSION = 1;


    public static final String NOTE_TABLE = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    public static String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY, COLUMN_DATE};

    public static final String CREATE_TABLE_NOTE = "CREATE TABLE " + NOTE_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_CATEGORY + " integer not null, "
            + COLUMN_DATE + " date);";

    private SQLiteDatabase sqlDB;
    private Context context;

    private NotebookDbHelper notebookHelper;

    public NotebookDbAdapter(Context context) {
        this.context = context;
    }

    public NotebookDbAdapter open() throws SQLException {
        notebookHelper = new NotebookDbHelper(context);
        sqlDB = notebookHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        notebookHelper.close();
    }

    // Add this in  class "NotebookDbAdapter"

    public List<Note> getAllNotes()
    {
        List<Note> notes = new ArrayList<Note>();
        try (Cursor cursor = sqlDB.query(NOTE_TABLE, allColumns, null, null, null, null, null)) {

            // TODO parcourt de la dernière à la première, pkoi?
            for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
                Note note = cursorToNote(cursor);
                notes.add(note);
            }
        }
        return  notes;
    }

    private Note cursorToNote(Cursor cursor)
    {
        Note newnote = new Note(cursor.getString(1), cursor.getString(2),
                Note.Category.getCategoryFromLabel(cursor.getString(3)),
                cursor.getLong(4), cursor.getLong(5));

        return newnote;
    }

    private static class NotebookDbHelper extends SQLiteOpenHelper {
        NotebookDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * called when the DB is created the very fisrt time
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_NOTE);
        }

        /**
         * This is a migration script I would say
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(NotebookDbHelper.class.getName(), "Upgrding database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");

            //destroys data
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);
        }
    }

}
