package com.example.noah


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class Borad_DBManager(
    context: Context
) : SQLiteOpenHelper(context, "board_DB", null, 1) {
    companion object {
        private const val TABLE_NAME = "board"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENTS = "contents"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_CONTENTS TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, olVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}