package com.ass2.water_reminderapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UpdateDpActivity extends AppCompatActivity {

    ImageView dp;
    Button upload;
    Bitmap bitmap;
    String encodeImageString;
    String Username, Password;
    byte[] bytearray;
    //String encodeImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dp);

        dp = findViewById(R.id.dp3);
        upload = findViewById(R.id.dpchat3);
        Username = getIntent().getStringExtra("username");
        Password = getIntent().getStringExtra("password");


        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(UpdateDpActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bytearray == null) {

                    Toast.makeText(UpdateDpActivity.this, "Please Select Image first", Toast.LENGTH_LONG).show();
                } else {
                    uploaddatatodb();
                }

            }
        });
    }




        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
        {
            if(requestCode==1 && resultCode==RESULT_OK)
            {
                Uri filepath=data.getData();
                try
                {
                    InputStream inputStream=getContentResolver().openInputStream(filepath);
                    bitmap= BitmapFactory.decodeStream(inputStream);
                    dp.setImageBitmap(bitmap);
                    encodeBitmapImage(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    bytearray = stream.toByteArray();
                }catch (Exception ex)
                {

                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }



    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    public void uploaddatatodb(){


        StringRequest request=new StringRequest(Request.Method.POST, "http://"+IPServer.getIP_server()+"/smdproj/UpdateImg.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                dp.setImageResource(R.drawable.dp_upload);
                bytearray=null;
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                Log.i("Response:", response);

                finish();

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
                map.put("image",encodeImageString);
                map.put("username",Username);
                map.put("password",Password);
                // map.put("PhoneNum",phone);

                //map.put("Username",Username);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }








}