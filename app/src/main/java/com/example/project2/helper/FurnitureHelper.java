package com.example.project2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project2.database.Database;
import com.example.project2.models.Furniture;

import java.util.Vector;


public class FurnitureHelper {
    private final String table = "Product";
    private Database data;
    private SQLiteDatabase sql;

    public FurnitureHelper(Context context) { data = new Database(context); }

    //insert
    public void insertProduct(Furniture furniture) {
        sql = data.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ProductImage", furniture.getProductimage());
        contentValues.put("ProductName", furniture.getProductname());
        contentValues.put("ProductRating", furniture.getProductrating());
        contentValues.put("ProductPrice", furniture.getProductprice());
        contentValues.put("ProductDescription", furniture.getProductdescription());

        sql.insert(table, null, contentValues);
        sql.close();
    }

    //read
    public Vector<Furniture> readProduct(){
        Vector<Furniture> productList = new Vector<>();
        sql = data.getReadableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM Product", null);
        cursor.moveToFirst();

        Furniture temp;
        String imgtemp;
        String nametemp;
        String desctemp;
        Double ratingtemp;
        Integer pricetemp;

        if (cursor != null && cursor.getCount() > 0){
            do {
                imgtemp = cursor.getString(cursor.getColumnIndexOrThrow("ProductImage"));
                nametemp = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
                ratingtemp = cursor.getDouble(cursor.getColumnIndexOrThrow("ProductRating"));
                pricetemp = cursor.getInt(cursor.getColumnIndexOrThrow("ProductPrice"));
                desctemp = cursor.getString(cursor.getColumnIndexOrThrow("ProductDescription"));

                temp = new Furniture(imgtemp, nametemp, ratingtemp, pricetemp, desctemp);
                productList.add(temp);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
            cursor.close();
        }
      sql.close();
        return productList;
    }
    public void delete(Furniture furniture){
        sql = data.getWritableDatabase();
        sql.delete(table, null, null);
        sql.close();
    }

}
