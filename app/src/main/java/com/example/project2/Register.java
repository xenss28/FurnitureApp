package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.database.Database;
import com.example.project2.helper.UserHelper;
import com.example.project2.models.User;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    EditText username;
    EditText password;
    EditText phone;
    Button btnLogin;
    Button btnRegister;
    Database database;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.edtRegisterEmail);
        username = findViewById(R.id.edtRegisterUsername);
        password = findViewById(R.id.edtRegisterPassword);
        phone = findViewById(R.id.edtRegisterPhone);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        userHelper = new UserHelper(this);
        btnRegister.setOnClickListener(this);

        btnRegister.setOnClickListener(view -> {
            boolean valid = true;

            String emailRegis = email.getText().toString();
            String usernameRegis = username.getText().toString();
            String phoneNum = phone.getText().toString();
            String passwordRegis = password.getText().toString();

            User newUser = new User(emailRegis, usernameRegis, phoneNum, passwordRegis);
            User temp1 = userHelper.ReadEmail(emailRegis);
            User temp2 = userHelper.ReadUsername(usernameRegis);

            if (emailRegis.isEmpty()){
                email.setError("email must not empty");
                valid = false;
            }
            else {
                if (!emailRegis.endsWith(".com")) {
                    email.setError("email must ends with .com");
                    valid = false;
                }
                else if (temp1 != null){
                    email.setError("email already been registered!");
                    valid = false;
                }
            }
            if (usernameRegis.isEmpty()){
                username.setError("username must not be empty");
                valid = false;
            }
            else {
                if (usernameRegis.length() < 3 || usernameRegis.length() > 20){
                    username.setError("username length must between 3 - 20 characters");
                    valid = false;
                }
                else if (temp2 != null){
                    username.setError("Username already been registered!");
                    valid = false;
                }
            }
            if (passwordRegis.isEmpty()){
                password.setError("password cannot be empty");
                valid = false;
            }
            else {
                if (!passwordRegis.matches("[a-zA-Z 0-9]+")){
                    password.setError("password must be alphanumeric");
                    valid = false;
                }
            }
            if (valid == true){
                userHelper.insert(newUser);
                Toast.makeText(Register.this, "Register Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            }
            else {
                Toast.makeText(Register.this, "User failed", Toast.LENGTH_LONG).show();
            }



        });
    }

    @Override
    public void onClick(View view) {

    }
}