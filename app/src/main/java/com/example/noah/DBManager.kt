package com.example.noah

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context
) : SQLiteOpenHelper(context, "Noah", null, 1) {

    companion object {

        private const val TABLE_BOARD = "board"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENTS = "contents"

        private const val TABLE_COMMENTS = "commentsDB"
        private const val COLUMN_COMMENTS_ID = "id"
        private const val COLUMN_BOARD_ID = "board_id"
        private const val COLUMN_COMMENTS = "comments"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블 board 생성
        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_BOARD (" +
                "$COLUMN_ID TEXT PRIMARY KEY, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_CONTENTS TEXT)")

        // 테이블 commentsDB 생성
        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_COMMENTS (" +
                "$COLUMN_COMMENTS_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_BOARD_ID TEXT, " +
                "$COLUMN_COMMENTS TEXT, " +
                "FOREIGN KEY($COLUMN_BOARD_ID) REFERENCES $TABLE_BOARD($COLUMN_ID))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 데이터베이스 버전이 변경되었을 때 업그레이드 로직을 구현할 수 있습니다.
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_COMMENTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOARD")
        onCreate(db)
    }
}