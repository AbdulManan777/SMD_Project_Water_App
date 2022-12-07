package com.ass2.water_reminderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;

public class Activity1 extends AppCompatActivity {




    EditText phone,password,username;
    Button continuebutton;
    String phoneNo;
    CountryCodePicker ccp;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);


        phone = findViewById(R.id.phonenum);
        continuebutton = findViewById(R.id.continuebutton);
        ccp = findViewById(R.id.ccp);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity1.this,SignUp.class));
            }
        });


        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneNo = ccp.getTextView_selectedCountry().getText().toString() + phone.getText().toString();
                Log.i("Phoe  um",phoneNo);
                if (!username.getText().toString().equals("") && !password.getText().toString().equals("") && !phoneNo.equals("")) {


                    StringRequest stringRequest=new StringRequest(Request.Method.POST,
                            "http://"+IPServer.getIP_server()+"/smdproj/login.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if (response.equals("Success")) {

                                        Intent i = new Intent(Activity1.this, Activity3.class);
                                        i.putExtra("Username",username.getText().toString());
                                        i.putExtra("Password",password.getText().toString());
                                        startActivity(i);
                                        // finish();

                                    } else if (response.equals("Failure")) {

                                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Unable to Connect to Server",Toast.LENGTH_LONG).show();
                        }
                    })
                    {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params=new HashMap<>();
                            params.put("Username",username.getText().toString());
                            params.put("Password",password.getText().toString());
                            params.put("Phonenum",phoneNo);
                            // params.put("PhoneNum",phonenum.getText().toString());

                            return params;
                        }
                    };

                    RequestQueue queue= Volley.newRequestQueue(Activity1.this);
                    queue.add(stringRequest);

                } else {

                    Toast.makeText(getApplicationContext(),"No field should be empty",Toast.LENGTH_LONG).show();
                }


                }




        });
    }
}