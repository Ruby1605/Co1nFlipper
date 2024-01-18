package com.example.co1nflipper


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
class CoinFlipDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "coinflip.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_RESULTS = "results"
        const val COLUMN_ID = "_id"
        const val COLUMN_RESULT = "result"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_RESULTS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_RESULT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    // Insertar un resultado en la base de datos
    fun insertarResultados(result: String) {
        val values = ContentValues().apply {
            put(COLUMN_RESULT, result)
        }
        writableDatabase.use { db ->
            db.insert(TABLE_RESULTS, null, values)
        }
    }
    // Obtener todos los resultados de la base de datos
    fun getResultados(): Cursor {
        val query = "SELECT * FROM $TABLE_RESULTS"
        return readableDatabase.rawQuery(query, null)
    }
    // Eliminar un resultado de la base de datos
    fun borrarResultados(resultId: Long) {
        writableDatabase.delete(TABLE_RESULTS, "$COLUMN_ID=?", arrayOf(resultId.toString()))
    }
    fun borrarResultadoPorValor(resultado: String): Int {
        return writableDatabase.delete(TABLE_RESULTS, "$COLUMN_RESULT=?", arrayOf(resultado))
    }
}