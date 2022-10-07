package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.database.Database;
import com.example.project2.helper.UserHelper;
import com.example.project2.models.User;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    EditText password;
    Button btnRegister;
    Button btnLogin;
    Database database;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.edtLoginEmail);
        password = findViewById(R.id.edtLoginPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        userHelper = new UserHelper(this);
        btnRegister.setOnClickListener(this);

        btnLogin.setOnClickListener(view -> {

            boolean valid = true;
            String emailLogin = email.getText().toString();
            String passwordLogin = password.getText().toString();

            User user = userHelper.Read(emailLogin, passwordLogin);
            String temp = "";


            if (emailLogin.length() == 0) {
                email.setError("email must be filled in!");
                valid = false;
            }
            else {
                if (user == null){
                    email.setError("this account hasn't registered!");
                    valid = false;
                }
                else {
                    temp = user.getPassword();
                    valid = true;
                }
            }

            if (passwordLogin.length() == 0) {
                password.setError("password must be filled in!");
                valid = false;
            }
            else {
                if (!passwordLogin.equals(temp)) {
                    password.setError("password incorrect bang!");
                    valid = false;
                }
            }
            if (valid == true){
                Intent h = new Intent(Login.this, Profile.class);
                h.putExtra("users", user);
                startActivity(h);
            }
            else {
                Toast.makeText(Login.this, "User failed", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister){
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        }
    }
}