package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class required_water_intake extends AppCompatActivity {

    String Age;
    String Gender;
    List<user_required_water_info> ls;
    Adapter adapter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_water_intake);
        // getting data from userInputs activity
        //Intent i = new Intent();
        Age = getIntent().getStringExtra("age");
        Gender = getIntent().getStringExtra("gender");
       // ls=new ArrayList<>();
        Calendar calender= Calendar.getInstance();

        Log.i("Hello g,",Age);
        Log.i("Hello g,",Gender);

       // Log.i("Hello g,", String.valueOf(glass));



        user_required_water_info u=new user_required_water_info();
        u.user_required_water_infoAdd(Age,Gender);
        int glass=u.getNo_of_water_glasses();
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
            Log.i("Value of Calender: ", String.valueOf(ls.get(f).getTime1()));
            adapter.notifyDataSetChanged();
           //
           // adapter.notifyDataSetChanged();
            calender.add(Calendar.HOUR_OF_DAY,1);


        }




       // ls.add(new user_required_water_info(Age,Gender));

    }
}