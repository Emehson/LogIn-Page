package com.example.emeh_son.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSignin, btnRegister, btnSignin2, btnRegister2;
    private EditText logemail, logpassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignin2 = (Button) findViewById(R.id.btnSignin2);
        btnRegister2 = (Button) findViewById(R.id.btnRegister2);
        logemail = (EditText) findViewById(R.id.logemail);
        logpassword = (EditText) findViewById(R.id.logpassword);




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Registration.class));

                Toast toast = Toast.makeText(getApplicationContext(),
                "Waiting...",
                        Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.BOTTOM,0,5);
            }
        });


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Welcome.class));
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Toast toast = Toast.makeText(getApplication(),
                        "connecting...",
                        Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.BOTTOM, 0,10);

            }
        });



    }
}
