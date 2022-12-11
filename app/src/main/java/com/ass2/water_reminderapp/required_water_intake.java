package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class required_water_intake extends AppCompatActivity {

    String Age;
    String Gender;
    String username,password;
    List<user_required_water_info> ls;
    Adapter adapter;
    RecyclerView rv;
    List<String> waterTime;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calender;
    int glass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_water_intake);
        // getting data from userInputs activity
        //Intent i = new Intent();
        createNotificationChannel();
        Age = getIntent().getStringExtra("age");
        Gender = getIntent().getStringExtra("gender");
       // ls=new ArrayList<>();
         calender= Calendar.getInstance();

        Log.i("Hello g,",Age);
        Log.i("Hello g,",Gender);

       // Log.i("Hello g,", String.valueOf(glass));

        username=getIntent().getStringExtra("username");
        password=getIntent().getStringExtra("password");



        user_required_water_info u=new user_required_water_info();
        u.user_required_water_infoAdd(Age,Gender);
         glass=u.getNo_of_water_glasses();
        Log.i("Glasses: ",String.valueOf(glass));
        rv = findViewById(R.id.rv);
        // setting layout Manager for Recycler View
        RecyclerView.LayoutManager lm=new LinearLayoutManager(required_water_intake.this);
        ls=new ArrayList<>();
        adapter=new Adapter(required_water_intake.this,ls);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);


        int f=0;
        for(int i1=1,j=9;i1<=glass;i1++,j++,f++){

            user_required_water_info u2=new user_required_water_info();
             u2.user_required_water_infoAdd(Age,Gender);



            calender.set(Calendar.HOUR_OF_DAY, j);
            calender.set(Calendar.MINUTE,0);
            calender.set(Calendar.SECOND,0);
            calender.set(Calendar.MILLISECOND,0);
            u2.setCalender(i1,calender);




            ls.add(u2);
          //  waterTime.add(ls.get(f).getTime1());
            Log.i("Value of Calender: ", String.valueOf(ls.get(f).getTime1()));
            adapter.notifyDataSetChanged();
           //
           // adapter.notifyDataSetChanged();
            calender.add(Calendar.HOUR_OF_DAY,1);


        }

        storeinDB();
        generateNotification();




       // ls.add(new user_required_water_info(Age,Gender));

    }

    private void storeinDB() {



        StringRequest request=new StringRequest(Request.Method.POST, "http://"+IPServer.getIP_server()+"/smdproj/addglassofwater.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {


                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                Log.i("Error:",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map=new HashMap<String, String>();
                map.put("Username",username);
                map.put("Password",password);
                map.put("Glass", String.valueOf(glass));
                map.put("Age",Age);
                map.put("Gender",Gender);
                //map.put("Username",Username);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }

    private void generateNotification() {

        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this,BedtimeNotify.class);

        pendingIntent= PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

      //  Toast.makeText(this,"Bed time Successfully Set", Toast.LENGTH_LONG).show();
    }

    private void createNotificationChannel() {



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name="WaterIntakeChannel";
            String description="Channel for Water Intake";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("waternotify",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}