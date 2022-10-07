package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project2.helper.UserHelper;
import com.example.project2.models.User;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    TextView id;
    TextView email;
    TextView username;
    TextView phone;
    EditText editusername;
    Button buttonu, buttons, buttond, buttonl;
    View layoutu, layoutue;

    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        id = findViewById(R.id.idprofile);
        email = findViewById(R.id.emailprofile);
        username = findViewById(R.id.usernameprofile);
        phone = findViewById(R.id.phoneprofile);
        editusername = findViewById(R.id.editusernameprofile);
        layoutu = findViewById(R.id.layoutusername);
        layoutue = findViewById(R.id.layoutusernameedit);
        buttonu = findViewById(R.id.buttonusername);
        buttons = findViewById(R.id.saveacc);
        buttond = findViewById(R.id.deleteacc);
        buttonl = findViewById(R.id.logout);

        layoutue.setVisibility(View.INVISIBLE);

        userHelper = new UserHelper(this);
        User user = getIntent().getParcelableExtra("users");

        id.setText(user.getId() + "");
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        phone.setText(user.getPhone());

        buttonu.setOnClickListener(view -> {
            layoutu.setVisibility(View.INVISIBLE);
            layoutue.setVisibility(View.VISIBLE);
        });

        buttons.setOnClickListener(view -> {
            String userinput = editusername.getText().toString();
            User temp = userHelper.ReadUsername(userinput);
            if (temp == null){
                user.setUsername(userinput);
                userHelper.update(user);
                username.setText(userinput);
                layoutue.setVisibility(View.INVISIBLE);
                layoutu.setVisibility(View.VISIBLE);
            }
            else {
                editusername.setError("bang udah ada");
            }

        });

        buttond.setOnClickListener(view -> {
            userHelper.delete(user);
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        });

        buttonl.setOnClickListener(view ->{
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        });





    }

}