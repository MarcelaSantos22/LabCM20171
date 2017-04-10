package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by admin on 1/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "eventos.db";
    SQLiteDatabase db;
    Cursor cursor;

    private static final String TABLE_CREATE_USER = "CREATE TABLE users (usuario TEXT PRIMARY KEY, contraseña TEXT NOT NULL, " +
            "correo TEXT NOT NULL, edad TEXT NOT NULL, foto BLOB)";

    private static final String TABLE_CREATE_EVENT = "CREATE TABLE events (codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "evento TEXT NOT NULL, descripcion TEXT NOT NULL, puntuacion , responsable ," +
            "fecha , ubicacion , infoGeneral , foto BLOB);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USER);
        db.execSQL(TABLE_CREATE_EVENT);
        this.db = db;
    }
    public long insertUsers(Users us){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario" , us.getUsuario());
        values.put("contraseña" , us.getContraseña());
        values.put("correo" , us.getCorreo());
        values.put("edad" , us.getEdad());

       long resp = db.insert("users", null, values);
        db.close();

        return resp;
    }
    public long insertEvents(Events ev){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("evento" , ev.getNombre());
        values.put("descripcion" , ev.getDescripcion());
        values.put("puntuacion" , ev.getPuntuacion());
        values.put("responsable" , ev.getResponsable());
        values.put("fecha" , ev.getFecha());
        values.put("ubicacion" , ev.getUbicacion());
        values.put("infoGeneral" , ev.getInfoGeneral());

        long resp = db.insert("events", null, values);
        db.close();

        return resp;
    }
    public void insertImageUser(byte[] imageBytes) {
        ContentValues cv = new ContentValues();
        cv.put("foto", imageBytes);
        db.insert("users", null, cv);
    }

    public ArrayList<String> getUsersName(){
        db = this.getReadableDatabase();
        ArrayList<String> usersName = new ArrayList<String>();
        cursor = db.query("users",null,null,null,null,null,null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                String name = cursor.getString(cursor.getColumnIndex("usuario"));
                Log.d(name, "UsersName: ");
                usersName.add(name);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return usersName;
    }

     public String searchPass(String user){
         db = this.getReadableDatabase();
         String query = "SELECT usuario, contraseña FROM users";
         cursor = db.rawQuery(query, null);
         String us, pass;
         pass = "no encontrado";
         if (cursor.moveToFirst()){
             do {
                 us = cursor.getString(0);
                 if (us.equals(user)){
                     pass = cursor.getString(1);
                     break;
                 }
             }while (cursor.moveToNext());
         }
         return pass;
     }
    public Cursor getAllEvents() {
        db = this.getReadableDatabase();
        cursor = db.query("events",null,null,null,null,null,null);

        return cursor;
    }
    public ArrayList<String> searchInfoUser(String user){
        db = this.getReadableDatabase();
        ArrayList<String> arList = new ArrayList<String>();
        String query = "SELECT usuario, edad, correo FROM users WHERE usuario=?";
        cursor = db.rawQuery(query, new String[]{user});
        String us, correo, edad;
        correo = "no encontrado";
        edad = "no encontrado";
        if (cursor.moveToFirst()){
            do {
                us = cursor.getString(0);
                if (us.equals(user)){
                    edad = cursor.getString(1);
                    correo = cursor.getString(2);
                    arList.add(edad);
                    arList.add(correo);
                    break;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryUs = "DROP TABLE IF EXISTS users";
        String queryEv = "DROP TABLE IF EXISTS events";
        db.execSQL(queryUs);
        db.execSQL(queryEv);
        this.onCreate(db);
    }
}
