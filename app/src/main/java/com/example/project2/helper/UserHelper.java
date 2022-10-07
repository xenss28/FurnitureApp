package com.example.project2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project2.database.Database;
import com.example.project2.models.User;

public class UserHelper {
    private final String table = "Users";
    private Database data;
    private SQLiteDatabase sql;

    public UserHelper(Context context) { data = new Database(context); }

    //insert
    public void insert(User user) {
        sql = data.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserEmailAddress", user.getEmail());
        contentValues.put("UserUsername", user.getUsername());
        contentValues.put("UserPhoneNumber", user.getPhone());
        contentValues.put("UserPassword", user.getPassword());

        sql.insert(table, null, contentValues);
        sql.close();
    }


    //read
    public User Read (String email, String password) {
        sql = data.getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM Users WHERE UserEmailAddress = ? and UserPassword = ?",
                                        new String[]{email, password});

        User user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            cursor.close();
        }
        sql.close();
        return user;
    }

    public User ReadEmail (String email) {
        sql = data.getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM Users WHERE UserEmailAddress = ?",
                                    new String[]{email});

        User user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            user.setPassword(cursor.getString(4));
        }

        sql.close();
        return user;
    }

    public User ReadUsername (String username) {
        sql = data.getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM Users WHERE UserUsername = ?",
                                    new String[]{username});

        User user = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            user.setPassword(cursor.getString(4));
        }
        sql.close();
        return user;
    }

    public void update(User user){
        sql = data.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserUsername", user.getUsername());
        sql.update(table, contentValues, "UserID = ?", new String[]{user.getId() + ""});
        sql.close();
    }

    public void delete(User user){
        sql = data.getWritableDatabase();
        sql.delete(table, "UserID = ?", new String[]{user.getId() + ""});
        sql.close();
    }

}
