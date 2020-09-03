package com.example.todonotes.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todonotes.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    ImageView imageWeb, imageGithub,imageInsta, imageTwitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        setOnClickListner();

    }


    private void init() {
    imageWeb = findViewById(R.id.web);
    imageGithub = findViewById(R.id.github);
    imageInsta = findViewById(R.id.insta);
    imageTwitter = findViewById(R.id.twitter);

    }
    private void setOnClickListner() {

        imageWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Website is in progress", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

        imageGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Github is in progress", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });


        imageInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/parwej__ansari/")));

            }
        });

        imageTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/parwej__ansari")));
            }
        });
    }



}
