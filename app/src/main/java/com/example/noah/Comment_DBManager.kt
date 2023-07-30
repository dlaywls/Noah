package com.example.noah

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Comment_DBManager( context: Context
) : SQLiteOpenHelper(context, "comments_DB", null, 1) {
    companion object {
        private const val TABLE_NAME = "commentsDB"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_COMMENTS = "comments"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_COMMENTS TEXT," +
                "FOREIGN KEY($COLUMN_TITLE) REFERENCES board(id))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, olVersion: Int, newVersion: Int) {

    }

}