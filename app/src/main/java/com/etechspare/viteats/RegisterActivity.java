package com.etechspare.viteats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password, name;
    MaterialButton user, owner;
    DBHelper dbHelper =new DBHelper(this);
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name= findViewById(R.id.name);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        owner= findViewById(R.id.owner);
        user = findViewById(R.id.user);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        owner.setOnClickListener(view -> {
            String user =username.getText().toString();
            String pass= password.getText().toString();
            String fullname= name.getText().toString();
            String type= "1";


            if(user.equals("")||pass.equals("")||fullname.equals(""))
                Toast.makeText(RegisterActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
            else{
                dbHelper.insertData(user, pass, type, fullname);
                Toast.makeText(RegisterActivity.this,"Owner add Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        user.setOnClickListener(view -> {
            String user =username.getText().toString();
            String pass= password.getText().toString();
            String fullname= name.getText().toString();
            String type= "2";


            if(user.equals("")||pass.equals("")||fullname.equals(""))
                Toast.makeText(RegisterActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
            else{
                dbHelper.insertData(user, pass, type, fullname);
                Toast.makeText(RegisterActivity.this,"User add Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}