package com.example.project2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "databasesql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsers = "CREATE TABLE Users (UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserEmailAddress TEXT not null," +
                "UserUsername TEXT not null," +
                "UserPhoneNumber TEXT not null," +
                "UserPassword TEXT not null)";

        sqLiteDatabase.execSQL(createUsers);

        String createProduct = "CREATE TABLE Product (ProductName TEXT PRIMARY KEY AUTOINCREMENT," +
                "ProductRating REAL not null," +
                "ProductPrice INTEGER not null," +
                "ProductImage TEXT not null," +
                "ProductDescription TEXT not null)";

        sqLiteDatabase.execSQL(createProduct);
//
//        String createTransaction = "CREATE TABLE "
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createUsers = "DROP TABLE IF EXISTS Users";
        sqLiteDatabase.execSQL(createUsers);
        String createProduct = "DROP TABLE IF EXISTS Product";
        sqLiteDatabase.execSQL(createProduct);
    }
}
