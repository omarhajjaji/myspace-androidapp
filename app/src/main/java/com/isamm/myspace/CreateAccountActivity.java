package com.isamm.myspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText lNameEdit;
    private EditText ageEdit;
    private EditText loginEdit;
    private EditText passwordEdit;

    private Button crtAccntBtn;

    private String name;
    private String lastname;
    private int age;
    private String login;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

        crtAccntBtn = findViewById(R.id.buttonCrtAcc);

        nameEdit = findViewById(R.id.fnameEditText);
        lNameEdit = findViewById(R.id.lnameEditText);
        ageEdit = findViewById(R.id.ageEditText);
        loginEdit = findViewById(R.id.loginEditText);
        passwordEdit = findViewById(R.id.passwordEditText);

        crtAccntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                lastname = lNameEdit.getText().toString();
                age = Integer.parseInt(ageEdit.getText().toString());
                login = loginEdit.getText().toString();
                password = passwordEdit.getText().toString();

                //TODO: Test if fields are not empty
                MainMenu.player = new Player(getApplicationContext(),name,lastname,login,password,age);
                Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}