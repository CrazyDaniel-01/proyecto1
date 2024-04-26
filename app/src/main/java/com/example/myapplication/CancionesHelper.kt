package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.prefs.PreferencesFactory

class CancionesHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table cancion(id integer primary key autoincrement, titulo text, cantante text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}