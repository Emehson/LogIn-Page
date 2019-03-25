package com.example.emeh_son.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    private Button btnSignin2, btnRegister2;
    private EditText etfname, etlname, etemail, etpassword, etphone;
    private String fname, lname, email, password, phonenumber;
    private KProgressHUD hud;
    private String status, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        AndroidNetworking.initialize(getApplicationContext());

        btnSignin2 = (Button) findViewById(R.id.btnSignin2);
        btnRegister2 = (Button) findViewById(R.id.btnRegister2);
        etfname = (EditText) findViewById(R.id.etfname);
        etlname = (EditText) findViewById(R.id.etlname);
        etemail = (EditText) findViewById(R.id.etemail);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etphone = (EditText) findViewById(R.id.etphone);

        btnSignin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, MainActivity.class));
                Toast.makeText(Registration.this, "Sign In Button Clicked", Toast.LENGTH_SHORT).show();
                validate();
            }
        });


        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Registration.this,Welcome.class));
                Toast.makeText(Registration.this, "Regisrtratin Button Clicked", Toast.LENGTH_SHORT).show();
                validate();
            }
        });
    }


    private void validate() {
        fname = etfname.getText().toString().trim();
        lname = etlname.getText().toString().trim();
        phonenumber = etphone.getText().toString().trim();
        email = etemail.getText().toString().trim();
        password = etpassword.getText().toString().trim();

        if (isconstainit(fname) == 0
                || isconstainit(email) == 0
                || isconstainit(password) == 0
                || isconstainit(lname) == 0
                || isconstainit(phonenumber) == 0) {

            Toast.makeText(this, "All Field Are Required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fname)) {
            etfname.setError("Please Enter Your First Name");
            return;
        }
        if (TextUtils.isEmpty(lname)) {
            etlname.setError("Please Enter Your Last Name");
            return;
        }
        if (TextUtils.isEmpty(phonenumber)) {
            etphone.setError("Please Enter Your Phone Number");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            etemail.setError("Please Enter Your Email Address");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etpassword.setError("Your Password Cannot be Less Than Six");
            return;
        }  else if (isNetworkAvaliable()) {
            send_request(fname, lname, email, phonenumber, password);
        } else {
            Toast.makeText(this, "Network Is Not Avalaible", Toast.LENGTH_SHORT).show();
        }


        }

   //AndroidNetworking.post(url: "https//logios.herokuapp.com/api/register")

    private void send_request(String fname, String lname, String email, String password, String phone_no) {
        hud.create(Registration.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Registering..." +" " + lname + " " + fname)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        AndroidNetworking.post("https://logios.herokuapp.com/api/register")
                .addBodyParameter("lname", lname)
                .addBodyParameter("fname", fname)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .addBodyParameter("phone_no", phone_no)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
            // do anything with response
            try {
                status = response.getString("status");
                message = response.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("signup", status);
            Log.d("signup2", message);
        }
        @Override
        public void onError(ANError error) {
            // handle error
        }
    });

     }


    private boolean isNetworkAvaliable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public int isconstainit(String edittext) {
        int number;
        edittext = edittext.trim();
        if (edittext.equals(" ")
                || edittext.length() == 0
                || edittext.equals("")
                || TextUtils.isEmpty(edittext)) {
            number = 0;
        } else {
            number = 1;

        }
        return number;
    }
}