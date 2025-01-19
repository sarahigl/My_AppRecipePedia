package com.example.myapplication.Data.Local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "recipepedia.db";
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
    private static final class tableRequeteIA {
        public static final String TABLE_NAME = "Requete_IA";
        public static final String COLUMN_ID = "id_requete_ia";
        public static final String COLUMN_REQUEST = "corp_requete";
        public static final String COLUMN_DATE = "date_requete";
        public static final String COLUMN_STATUS = "status_requete";
        public static final String COLUMN_ID_USER = "id_utilisateur";
    }
    public static final class tableReponseIA {
        public static final String TABLE_NAME = "Reponse_IA";
        public static final String COLUMN_ID = "id_reponse_ia";
        public static final String COLUMN_RESPONSE = "corp_reponse";
        public static final String COLUMN_DATE = "date_reponse";
        public static final String COLUMN_ID_REQUEST_IA = "id_requete_ia";
    }
    public static final class tableFavorisIA {
        public static final String TABLE_NAME = "Favoris_IA";
        public static final String COLUMN_ID = "id_favoris_ia";
        public static final String COLUMN_ADD_DATE = "date_ajout";
        public static final String COLUMN_ID_USER = "id_utilisateur";
        public static final String COLUMN_ID_RESPONSE_IA = "id_reponse_ia";
    }


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table Utilisateur
        String createTableUtilisateur = "CREATE TABLE " + tableUtilisateur.TABLE_NAME + " (" +
                tableUtilisateur.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableUtilisateur.COLUMN_USERNAME + " TEXT NOT NULL, " +
                tableUtilisateur.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
                tableUtilisateur.COLUMN_LASTNAME + " TEXT, " +
                tableUtilisateur.COLUMN_NAME + " TEXT, " +
                tableUtilisateur.COLUMN_PASSWORD + " TEXT NOT NULL);";
        db.execSQL(createTableUtilisateur);

        // Table Requete_IA
        String createTableRequeteIA = "CREATE TABLE " + tableRequeteIA.TABLE_NAME + " (" +
                tableRequeteIA.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableRequeteIA.COLUMN_REQUEST + " TEXT NOT NULL, " +
                tableRequeteIA.COLUMN_DATE + " TEXT NOT NULL, " +
                tableRequeteIA.COLUMN_STATUS + " TEXT, " +
                tableRequeteIA.COLUMN_ID_USER + " INTEGER, " +
                "FOREIGN KEY (" + tableRequeteIA.COLUMN_ID_USER + ") " +
                "REFERENCES " + tableUtilisateur.TABLE_NAME + " (" + tableUtilisateur.COLUMN_ID + "));";
        db.execSQL(createTableRequeteIA);

        // Table Reponse_IA
        String createTableReponseIA = "CREATE TABLE " + tableReponseIA.TABLE_NAME + " (" +
                tableReponseIA.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableReponseIA.COLUMN_RESPONSE + " TEXT NOT NULL, " +
                tableReponseIA.COLUMN_DATE + " TEXT NOT NULL, " +
                tableReponseIA.COLUMN_ID_REQUEST_IA + " INTEGER, " +
                "FOREIGN KEY (" + tableReponseIA.COLUMN_ID_REQUEST_IA + ") " +
                "REFERENCES " + tableRequeteIA.TABLE_NAME + " (" + tableRequeteIA.COLUMN_ID + ") ON DELETE CASCADE);";
        db.execSQL(createTableReponseIA);

        // Table Favoris_IA
        String createTableFavorisIA = "CREATE TABLE " + tableFavorisIA.TABLE_NAME + " (" +
                tableFavorisIA.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                tableFavorisIA.COLUMN_ADD_DATE + " TEXT NOT NULL, " +
                tableFavorisIA.COLUMN_ID_USER + " INTEGER, " +
                tableFavorisIA.COLUMN_ID_RESPONSE_IA + " INTEGER, " +
                "FOREIGN KEY (" + tableFavorisIA.COLUMN_ID_USER + ") " +
                "REFERENCES " + tableUtilisateur.TABLE_NAME + " (" + tableUtilisateur.COLUMN_ID + "), " +
                "FOREIGN KEY (" + tableFavorisIA.COLUMN_ID_RESPONSE_IA + ") " +
                "REFERENCES " + tableReponseIA.TABLE_NAME + " (" + tableReponseIA.COLUMN_ID + "));";
        db.execSQL(createTableFavorisIA);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableUtilisateur.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tableRequeteIA.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tableReponseIA.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tableFavorisIA.TABLE_NAME);
        onCreate(db);
        }
    public Cursor getUserFavorites(int userId) {
        //cursor => un pointeur qui se déplace à travers les lignes d'un tableau de données, vous permettant de lire les valeurs de chaque colonne pour chaque ligne
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Favoris_IA " +
                "INNER JOIN Reponse_IA ON Favoris_IA.id_reponse_ia = Reponse_IA.id_reponse_ia " +
                "WHERE Favoris_IA.id_utilisateur = ?";
        // Exécutez la requête et obtenez le Cursor
        return db.rawQuery(query, new String[]{String.valueOf(userId)});
    }

}
