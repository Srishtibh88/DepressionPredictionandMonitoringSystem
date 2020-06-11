package com.ashish.emotionoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText name,email,password;
    Button signUp;
    SQLiteconn db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.signUpName);
        email=findViewById(R.id.signUpEmail);
        password=findViewById(R.id.signUpPassword);
        signUp=findViewById(R.id.signUpButton);

        db=new SQLiteconn(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpFun();
            }
        });
    }

    private void signUpFun() {

        String n=name.getText().toString();
        String phone=email.getText().toString();
        String pass=password.getText().toString();

        if(n.trim().equals("")||phone.trim().equals("")||pass.trim().equals("")){
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
        else{
            if(db.createUser(n,phone,pass)){
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,Dashboard.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            else{
                Toast.makeText(this, "Some problem occured", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
