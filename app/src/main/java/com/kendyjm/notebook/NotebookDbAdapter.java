package com.kendyjm.notebook;

import android.content.ContentValues;
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
    private static final String TAG_LOG = NotebookDbAdapter.class.getName();

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
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private NotebookDbHelper notebookHelper;

    public NotebookDbAdapter(Context context) {
        this.context = context;
    }

    public NotebookDbAdapter open() throws SQLException {
        notebookHelper = new NotebookDbHelper(context);

        sqlDB = notebookHelper.getWritableDatabase();
       //notebookHelper.onUpgrade(sqlDB, 0, 1);
        return this;
    }

    public void close() {
        notebookHelper.close();
    }

    public Note createNote(String title, String message, Note.Category category) {
        // instanciation de la nouvelle note
        Note newNote = new Note(-1, title, message, category, System.currentTimeMillis());

        // insert en base
        ContentValues values = noteToContentValues(newNote);
        long insertId = sqlDB.insert(NOTE_TABLE, null, values);

        // maj de l'id après insertion
        newNote.setNoteId(insertId);

        Log.i(TAG_LOG, "created Note : " + newNote);
        return  newNote;
    }

    /**
     *
     * @param idToUpdate
     * @param newTitle
     * @param newMessage
     * @param newCategory
     * @return the number of rows that have been updated
     */
    public long updateNote(long idToUpdate, String newTitle, String newMessage, Note.Category newCategory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newTitle);
        values.put(COLUMN_MESSAGE, newMessage);
        values.put(COLUMN_CATEGORY, newCategory.getLabel());
        values.put(COLUMN_DATE, System.currentTimeMillis() + "");

        Log.i(TAG_LOG, "updateNote id=" + idToUpdate + " whith values: " + values);

        return sqlDB.update(NOTE_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);
    }


    public long deleteNote(long idToDelete) {
        Log.i(TAG_LOG, "deleteNote id=" + idToDelete);

        return sqlDB.delete(NOTE_TABLE, COLUMN_ID + " = " + idToDelete, null);
    }

    public List<Note> getAllNotes()
    {
        List<Note> notes = new ArrayList<Note>();

        Cursor cursor = sqlDB.query(NOTE_TABLE, allColumns, null, null, null, null, null);

            // parcourt de la dernière à la première, pour afficher la dernière note en haut de la liste
            for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
                Note note = cursorToNote(cursor);
                notes.add(note);
            }
        cursor.close();
        return  notes;
    }

    private Note cursorToNote(Cursor cursor)
    {
        Note newnote = new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                Note.Category.getCategoryFromLabel(cursor.getString(3)),
                cursor.getLong(4));

        return newnote;
    }

    /**
     *
     * @param noteToInsert
     * @return
     */
    private ContentValues noteToContentValues(Note noteToInsert)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, noteToInsert.getTitle());
        values.put(COLUMN_MESSAGE, noteToInsert.getMessage());
        values.put(COLUMN_CATEGORY, noteToInsert.getCategory().getLabel());
        values.put(COLUMN_DATE, noteToInsert.getDate());
        // no need to set ID, it is autoincrement

        return values;
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
