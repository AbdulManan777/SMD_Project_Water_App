package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class userInputs extends AppCompatActivity {


    TextView user_height;
    TextView user_weight,user_age;
    ImageView increment_age,decrement_age,increment_weight,decrement_weight;
    SeekBar mseekbarforuser_height;
    Button save_userInfo;
    RelativeLayout mmale,mfemale;
    String username,password;

    int intweight=60;
    int intage=21;
    int currentprogress;
    String mintprogress="150";
    String typerofuser="Male";
    String weight2="60";
    String age2="21";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inputs);

      //  getSupportActionBar().hide();
        user_age=findViewById(R.id.currentage);
        user_weight=findViewById(R.id.currentweight);
        user_height=findViewById(R.id.user_height);
        increment_age=findViewById(R.id.incrementage);
        decrement_age=findViewById(R.id.decrementage);
        increment_weight=findViewById(R.id.incremetweight);
        decrement_weight=findViewById(R.id.decrementweight);
        save_userInfo=findViewById(R.id.get_userInfo);
        mseekbarforuser_height=findViewById(R.id.seekbarforheight);
        mmale=findViewById(R.id.male);
        mfemale=findViewById(R.id.female);
        username=getIntent().getStringExtra("username");
        password=getIntent().getStringExtra("password");



        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typerofuser="Male";

            }
        });


        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typerofuser="Female";
            }
        });

        mseekbarforuser_height.setMax(300);
        mseekbarforuser_height.setProgress(170);
        mseekbarforuser_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentprogress=progress;
                mintprogress=String.valueOf(currentprogress);
                user_height.setText(mintprogress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        increment_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                user_weight.setText(weight2);
            }
        });

        increment_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage=intage+1;
                age2=String.valueOf(intage);
                user_age.setText(age2);
            }
        });


        decrement_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage=intage-1;
                age2=String.valueOf(intage);
                user_age.setText(age2);
            }
        });


        decrement_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                user_weight.setText(weight2);
            }
        });



        save_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(typerofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
                }
                else if(mintprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(),"Select Your height First",Toast.LENGTH_SHORT).show();
                }
                else if(intage==0 || intage<0)
                {
                    Toast.makeText(getApplicationContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();
                }

                else if(intweight==0|| intweight<0) //
                {
                    Toast.makeText(getApplicationContext(),"Weight Is Incorrect",Toast.LENGTH_SHORT).show();
                }
                else {


                    Intent intent = new Intent(userInputs.this, required_water_intake.class);
                    intent.putExtra("gender", typerofuser);
                    intent.putExtra("height", mintprogress);
                    intent.putExtra("weight", weight2);
                    intent.putExtra("age", age2);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);

                    startActivity(intent);

                    // will use web api here to save user info into database

                }


            }
        });


    }
}