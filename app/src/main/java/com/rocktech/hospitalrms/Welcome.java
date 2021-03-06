package com.rocktech.hospitalrms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    Animation top, bottom;
    ImageView hospital, school;
    TextView loading;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       // hospital = findViewById(R.id.hospital);
        school = findViewById(R.id.uniosun);

        loading = findViewById(R.id.loading);

        progressBar = findViewById(R.id.progressBar);

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

       // hospital.setAnimation(bottom);
        school.setAnimation(bottom);
        loading.setAnimation(bottom);
        progressBar.setAnimation(top);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, HomepageActivity.class);
                startActivity(intent);
                System.exit(0);
                finish();
                finishAffinity();
            }
        }, 7000);
    }
}
