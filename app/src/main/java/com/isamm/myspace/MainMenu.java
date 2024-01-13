package com.isamm.myspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class MainMenu extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar;
    public static Player player;
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get player data
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        //get the previous sharedPreference if it exists
        String rslt = sharedPreferences.getString("playerObject","");
        Gson gson = new Gson();
        player = gson.fromJson(rslt, Player.class);

        if(player == null){//if not
            //start the login activity
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main_menu);
        actionBar = getSupportActionBar();

        //loading the default fragment
        loadFragment(new HomeFragment());

        BottomNavigationView navigation = findViewById(R.id.navView);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                loadFragment(new HomeFragment());
                setTitle(getResources().getString(R.string.AppName));
                actionBar.setDisplayHomeAsUpEnabled(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                setTitle(getResources().getString(R.string.AppName));
                actionBar.setDisplayHomeAsUpEnabled(false);
                break;

            case R.id.navigation_account:
                fragment = new AccountFragment();
                setTitle(getResources().getString(R.string.account));
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;

            case R.id.navigation_quiz:
                fragment = new QuizFragment();
                setTitle(getResources().getString(R.string.quiz));
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
        }

        return loadFragment(fragment);
    }
}