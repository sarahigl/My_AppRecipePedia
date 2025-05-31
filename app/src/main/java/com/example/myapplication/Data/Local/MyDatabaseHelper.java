package com.example.myapplication.Data.Local;

import static com.example.myapplication.Data.Local.MyDatabaseHelper.tableMessage.COLUMN_MESSAGE;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.myapplication.Model.IA.Message;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "assistantRecipepedia.db";
    private static final int DATABASE_VERSION = 1;


    public static final class tableUtilisateur {
        public static final String TABLE_NAME = "Utilisateur";
        public static final String COLUMN_ID = "id_utilisateur";
        public static final String COLUMN_USERNAME = "pseudo_utilisateur";
        public static final String COLUMN_EMAIL = "email_utilisateur";
        public static final String COLUMN_LASTNAME = "nom_utilisateur";
        public static final String COLUMN_NAME = "prenom_utilisateur";
        public static final String COLUMN_PASSWORD = "mdp_utilisateur";
    }
    public static final class tableMessage {
        public static final String TABLE_NAME = "Message";
        public static final String COLUMN_ID = "id_message";
        public static final String COLUMN_MESSAGE = "contenu_message";
        public static final String COLUMN_DATE = "date_message";
        public static final String COLUMN_TYPE_MESSAGE = "type_message";
        public static final String COLUMN_ID_USER = "id_utilisateur";
    }

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUtilisateur = "CREATE TABLE IF NOT EXISTS " + tableUtilisateur.TABLE_NAME + " (" +
                tableUtilisateur.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableUtilisateur.COLUMN_USERNAME + " TEXT NOT NULL, " +
                tableUtilisateur.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
                tableUtilisateur.COLUMN_LASTNAME + " TEXT, " +
                tableUtilisateur.COLUMN_NAME + " TEXT, " +
                tableUtilisateur.COLUMN_PASSWORD + " TEXT NOT NULL);";
        db.execSQL(createTableUtilisateur);

        String createTableMessage = "CREATE TABLE IF NOT EXISTS " + tableMessage.TABLE_NAME + " (" +
                tableMessage.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableMessage.COLUMN_MESSAGE + " TEXT NOT NULL, " +
                tableMessage.COLUMN_DATE + " TEXT NOT NULL, " + //SQLite utilise le type TEXT pour stocker les dates
                tableMessage.COLUMN_TYPE_MESSAGE + " INTEGER NOT NULL, " +
                tableMessage.COLUMN_ID_USER + " INTEGER, " +
                "FOREIGN KEY (" + tableMessage.COLUMN_ID_USER + ") " +
                "REFERENCES " + tableUtilisateur.TABLE_NAME + " (" + tableUtilisateur.COLUMN_ID + "));";
        db.execSQL(createTableMessage);
    }
//V1 application le type de message sera toujours le meme => type = 0 (bot assistant IA)
    public long insertMessageData(String message, String date, int type, int idUser){
        String sql = "INSERT INTO " + tableMessage.TABLE_NAME + " ( " + COLUMN_MESSAGE + ", "
                + tableMessage.COLUMN_DATE +", " + tableMessage.COLUMN_TYPE_MESSAGE
                +", "+ tableMessage.COLUMN_ID_USER
                +") VALUES ( ?, ?, ?, ? );";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransactionNonExclusive();
        try {
            statement.bindString(1, message);
            statement.bindString(2, date);
            statement.bindLong(3, type);
            statement.bindLong(4, idUser);

            //afin de notifier l'utilisateur que l'ajout est fait avec succès ou non
            long rowId = statement.executeInsert();
            db.setTransactionSuccessful();
            return rowId;
        } finally {
            db.endTransaction();
            statement.close();
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableUtilisateur.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tableMessage.TABLE_NAME);
        onCreate(db);
        }

    public List<Message> getUserFavorites(int i) {
        List<Message> favorites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT " + tableMessage.COLUMN_MESSAGE + ", " + tableMessage.COLUMN_DATE +
                    " FROM " + tableMessage.TABLE_NAME +
                    " WHERE " + tableMessage.COLUMN_TYPE_MESSAGE + " = ? AND " + tableMessage.COLUMN_ID_USER + " = ?";

            cursor = db.rawQuery(query, new String[]{"0", String.valueOf(i)});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String messageText = cursor.getString(cursor.getColumnIndex(tableMessage.COLUMN_MESSAGE));
                    @SuppressLint("Range") String messageDate = cursor.getString(cursor.getColumnIndex(tableMessage.COLUMN_DATE));

                    Message message = new Message(messageText, messageDate);
                    favorites.add(message);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return favorites;

    }
}
