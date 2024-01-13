package com.isamm.myspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class PlanetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        Intent intent = getIntent();
        //Get planet data from previous activity
        String name = intent.getStringExtra("planetName");
        String description = intent.getStringExtra("planetDescription");
        String color = intent.getStringExtra("planetColor");
        String imgPath = intent.getStringExtra("planetImgPath");
        //setting the title
        setTitle(name);

        //Display planet data
        TextView planetName = findViewById(R.id.planetName);
        TextView planetDesc = findViewById(R.id.planetDesc);
        planetName.setText(name);
        planetDesc.setText(description);

        //setting the image
        ImageView planetImg = findViewById(R.id.planetImg);
        int planetImgId = getResources().getIdentifier(imgPath,"drawable",getPackageName());
        planetImg.setBackgroundResource(planetImgId);

        //set actionBar color
        int colorResourceId = getResources().getIdentifier(color,"color",getPackageName());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(colorResourceId)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources()
                    .getColor(colorResourceId));
        }


    }
}