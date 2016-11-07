package com.kendyjm.notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kendy on 07/11/16.
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

}
