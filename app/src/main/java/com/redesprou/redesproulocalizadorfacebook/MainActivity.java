package com.redesprou.redesproulocalizadorfacebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button searchButton;
    EditText searchEditText;
    Spinner numberOfResultsSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initViews(){
        loginButton = (LoginButton) findViewById(R.id.login_button);
        searchButton = (Button) findViewById(R.id.search_button);
        searchEditText = (EditText) findViewById(R.id.search_EditText);
        numberOfResultsSpinner = (Spinner) findViewById(R.id.search_Spinner );
    }
}
