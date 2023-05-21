package com.etechspare.viteats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    MaterialButton login, owner;
    DBHelper dbHelper =new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        login= findViewById(R.id.user);
        owner= findViewById(R.id.owner);

            dbHelper.insertData("user", "123", "1","User");
        dbHelper.insertData("owner", "123", "2","Owner");

        login.setOnClickListener(view -> {
            String user =username.getText().toString();
            String pass= password.getText().toString();
            String type= "1";


            if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
            else{
                Boolean checker=dbHelper.checkusernamepassword(user,pass, type);
                if(checker==true){
                    SharedPrefHelper.writeString(getApplicationContext(),"userType",type);
                    Toast.makeText(MainActivity.this,"User Sign in Successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                }
            }
        });
        owner.setOnClickListener(view -> {
            String user =username.getText().toString();
            String pass= password.getText().toString();
            String type= "2";

            if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
            else{
                Boolean checker=dbHelper.checkusernamepassword(user,pass, type);
                if(checker==true){
                    SharedPrefHelper.writeString(getApplicationContext(),"userType",type);
                    Toast.makeText(MainActivity.this,"Owner Sign in Successful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}