package com.ass2.water_reminderapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    TextView login;
    EditText username,pass,phone;
    Button signup;
    CountryCodePicker ccp;
    String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.login);
        username=findViewById(R.id.username1);
        pass=findViewById(R.id.password1);
        phone=findViewById(R.id.phonenum);
        ccp=findViewById(R.id.ccp1);
        signup=findViewById(R.id.signupbutton);

        Log.i(
                "Username is",username.getText().toString()
        );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity1.class));
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              phoneNo=ccp.getTextView_selectedCountry().getText().toString() + phone.getText().toString();


                if(!username.getText().toString().equals("")&& !pass.getText().toString().equals("") && !phoneNo.equals("")){

                    Intent i=new Intent(SignUp.this,DpUploadActivity.class);
                    i.putExtra("Username",username.getText().toString());
                    i.putExtra("Password",pass.getText().toString());
                    i.putExtra("Phone",phoneNo);
                    startActivity(i);




                 //   Log.i("Uerjarahu","Keseho");
                   /* StringRequest request=new StringRequest(
                            Request.Method.POST,
                            "http://"+IPServer.getIP_server()+"/smdproj/insert.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject obj=new JSONObject(response);
                                        if(obj.getInt("code")==1)
                                        {
                                            Toast.makeText(getApplicationContext(),"User added Successfully",Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(SignUp.this, Activity3.class);
                                            i.putExtra("Username",username.getText().toString());
                                            i.putExtra("Password",pass.getText().toString());
                                            // i.putExtra("Password",password.getText().toString());
                                            startActivity(i);
                                            //startActivity(new Intent(Signup_Activity.this,DpUploadActivity.class));
                                            //finish();
                                        }
                                        else{
                                            Toast.makeText(
                                                    SignUp.this,
                                                    obj.get("msg").toString()
                                                    ,Toast.LENGTH_LONG
                                            ).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        Toast.makeText(
                                                SignUp.this,
                                                "Incorrect JSON"
                                                ,Toast.LENGTH_LONG
                                        ).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(
                                            SignUp.this,
                                            error.toString().trim()
                                            , Toast.LENGTH_LONG
                                    ).show();
                                }
                            })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params=new HashMap<>();
                            params.put("Username",username.getText().toString());
                            params.put("Password",pass.getText().toString());
                            params.put("PhoneNum",phoneNo);

                            return params;
                        }
                    };
                    RequestQueue queue= Volley.newRequestQueue(SignUp.this);
                    queue.add(request);*/

                }

                else{

                    Toast.makeText(getApplicationContext(),"Please enter all the fields to contniue",Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}