package com.example.memebamzi;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pBar);


        String url = "https://meme-api.herokuapp.com/gimme/";
        StringRequest
                stringRequest
                = new StringRequest(url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        System.out.println(response);
                        Meme meme = gson.fromJson((String) response,Meme.class);
                        String ImgUrl = meme.getUrl();
                        //now creating image
                        img = findViewById(R.id.imageView);
                        Glide.with(img).load(ImgUrl).into(img);
                        pb.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, "Error :"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void NewMeme(View view) {


        pb.setVisibility(View.VISIBLE);
        String url = "https://meme-api.herokuapp.com/gimme/";
        StringRequest
                stringRequest
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        System.out.println(response);
                        Meme meme = gson.fromJson((String) response,Meme.class);
                        String ImgUrl = meme.getUrl();
                        //now creating image
                        img = findViewById(R.id.imageView);
                        Glide.with(img).load(ImgUrl).into(img);
                        pb.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, "Error :"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}