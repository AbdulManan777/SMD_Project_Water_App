package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Benefits extends AppCompatActivity {

    Button back;
    TextView txtview1, txtview2, txtview3, txtview4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefits);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity3.class));
            }
        });

        txtview1 = findViewById(R.id.txtview1);
        txtview2 = findViewById(R.id.txtview2);
        txtview3 = findViewById(R.id.txtview3);
        txtview4 = findViewById(R.id.txtview4);

        txtview1.setText("Energy Boosting");
        txtview2.setText("Digestive Efficiency");
        txtview3.setText("Kidney Function");
        txtview3.setText("Brain Function");
    }
}