package com.ass2.water_reminderapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ass2.water_reminderapp.databinding.Activity3Binding;
import com.ass2.water_reminderapp.databinding.ActivityMainBinding;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity3 extends AppCompatActivity {

    Activity3Binding binding;
    String username;
    DrawerLayout dwr;
    TextView logout;
    ImageView logoutimg;
    ImageView profile,profile_dp;
    TextView username4,password4,phone4;
    String url;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        dwr = findViewById(R.id.drwr);
        phone4=findViewById(R.id.phone4);
        password4=findViewById(R.id.password4);
        username4=findViewById(R.id.user_name4);
        profile=findViewById(R.id.profielpicture);
        profile_dp=findViewById(R.id.profile_dp);
        logout=findViewById(R.id.log_out);
        logoutimg=findViewById(R.id.logoutimg);


        username=getIntent().getStringExtra("Username");
        password=getIntent().getStringExtra("Password");

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dwr.isDrawerOpen(Gravity.LEFT))
                    dwr.closeDrawer(Gravity.LEFT);
                else
                    dwr.openDrawer(Gravity.LEFT);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        password4.setText(password);
        username4.setText(username);






        StringRequest request2=new StringRequest(Request.Method.POST, "http://"+IPServer.getIP_server()+"/smdproj/getImageOnly.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj=new JSONObject(response);

                            if(obj.getInt("code")==1)
                            {
                                JSONArray contacts=obj.getJSONArray("pictures");

                                for (int i=0; i<contacts.length();i++)
                                {

                                    JSONObject contact=contacts.getJSONObject(i);
                                    //String usern=contact.getString("Username");
                                    String image=contact.getString("image");
                                    url="http://"+IPServer.getIP_server()+"/smdproj/Dps/"+image;

                                    //MyContact m=new MyContact(usern,url);
                                    //ls.add(m);
                                    //adapter.notifyDataSetChanged();
                                }

                            }
                            else{
                                Toast.makeText(Activity3.this,obj.get("msg").toString(),Toast.LENGTH_LONG).show();
                            }
                            Glide.with(Activity3.this).load(url).into(profile);
                            Glide.with(Activity3.this).load(url).into(profile_dp);

                        } catch (JSONException e) {
                            Toast.makeText(Activity3.this,response,Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity3.this,"Error Connecting Server",Toast.LENGTH_LONG).show();
                    }
                }

        )
        {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
               // Log.i("Username",username);
                //Log.i("Password",password);
                params.put("Username",username);
                params.put("Password",password);
                // params.put("Password",password);
                // params.put("PhoneNum",phonenum.getText().toString());

                return params;
            }
        };


        RequestQueue queue2= Volley.newRequestQueue(Activity3.this);
        queue2.add(request2);





        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home)
                replaceFragment(new HomeFragment());

            else if (item.getItemId() == R.id.services) {



                replaceFragment(new ServicesFragment());
            }

            else if (item.getItemId() == R.id.settings)
                replaceFragment(new SettingsFragment());


            return true;

        });
    }

    private void replaceFragment(Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}