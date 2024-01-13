package com.isamm.myspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText login,password;
    private Button btn;
    private DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        login=findViewById(R.id.loginEditText);
        password=findViewById(R.id.passwordEditText);
        btn=findViewById(R.id.button);


        dbManager = new DatabaseManager(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lgn = login.getText().toString();
                String pwd = password.getText().toString();
                Player input = dbManager.readPlayerByLoginAndPwd(lgn,pwd);
                if (input!=null) {
                    MainMenu.player = input;
                    MainMenu.player.updateSharedPref(getApplicationContext());
                    Log.i("PlayerFromLoginActivity",MainMenu.player.toString());
                    openact(MainMenu.class);
                }else{
                    Toast.makeText(getApplicationContext(),R.string.error_login,Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        TextView btnHelp=findViewById(R.id.btnHelp);
        TextView crAccountBtn = findViewById(R.id.btnCreateAcc);
        crAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AideActivity.class);
                startActivity(intent);
            }
        });
    }


    private void openact(Class c) {
        Intent intent=new Intent(this,c);
        startActivity(intent);
        finish();
    }
}
