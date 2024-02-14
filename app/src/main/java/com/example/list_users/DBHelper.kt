package com.example.list_users

import Utilizador
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context):SQLiteOpenHelper(context,"database.db",null,1) {

    val sql = arrayOf(
        "CREATE TABLE utilizadores (id INT PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)",
        "INSERT INTO utilizadores(username,password) VALUES ('João','15421')",
        "INSERT INTO utilizadores(username,password) VALUES ('Maria','87541')"
    )
    override fun onCreate(db: SQLiteDatabase) {
       sql.forEach {
           db.execSQL(it)
       }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table utilizadores")
        onCreate(db)
    }

    fun utilizadorSelectAll():Cursor
    {
        val db=this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizadores",null)
        db.close()
        return c
    }

    fun utilizadorListSelectAll():ArrayList<Utilizador>
    {
        val db=this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizadores",null)
        val listaUtlizadores:ArrayList<Utilizador> = ArrayList()

        if(c.count>0)
        {
            c.moveToFirst()
            do{

               val idIndex=c.getColumnIndex("id")
                val usernameIndex=c.getColumnIndex("username")
                val passIndex=c.getColumnIndex("password")

                val id=c.getInt(idIndex)
                val username=c.getString(usernameIndex)
                val password=c.getString(passIndex)

              listaUtlizadores.add(Utilizador(id,username,password))

            }while (c.moveToNext())
        }
        db.close()
        return listaUtlizadores
    }
}