package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project2.buatRecycler.AdapterFurniture;
import com.example.project2.helper.FurnitureHelper;
import com.example.project2.helper.UserHelper;
import com.example.project2.models.Furniture;
import com.example.project2.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    UserHelper userHelper;
    FurnitureHelper furnitureHelper;
    RecyclerView recyclerView;
    TextView usernameLogin;
    static TextView errordata;
    AdapterFurniture furnitureAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        userHelper = new UserHelper(this);
        User user = getIntent().getParcelableExtra("users");
        furnitureHelper = new FurnitureHelper(this);

        errordata = findViewById(R.id.errorHome);
        usernameLogin = findViewById(R.id.mUsername);
        usernameLogin.setText(user.getUsername());
        recyclerView = findViewById(R.id.mRecycler);

        String link = "https://bit.ly/InSOrmaJSON";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(link, response -> {
            try {
                JSONArray furnitures = response.getJSONArray("furnitures");

                furnitureHelper.delete();

                for (int i = 0; i < furnitures.length(); i++){
                    JSONObject furniture = furnitures.getJSONObject(i);
                    String furnitureName = furniture.getString("product_name");
                    Double furnitureRating = furniture.getDouble("rating");
                    Integer furniturePrice = furniture.getInt("rating");
                    String furnitureImage = furniture.getString("image");
                    String furnitureDescription = furniture.getString("description");

                    Furniture newFurniture = new Furniture(furnitureImage, furnitureName, furnitureRating, furniturePrice, furnitureDescription);
                    furnitureHelper.insertProduct(newFurniture);
                }

                AdapterFurniture adapterFurniture = new AdapterFurniture(this, furnitureHelper.readProduct(), new AdapterFurniture.() {

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });
    }
}