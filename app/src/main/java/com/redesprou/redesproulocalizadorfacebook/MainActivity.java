package com.redesprou.redesproulocalizadorfacebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button searchButton;
    EditText searchEditText;
    Spinner numberOfResultsSpinner;
    CallbackManager callBackManager;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callBackManager = CallbackManager.Factory.create();
        List<String> permitions = Arrays.asList("email", "public_profile", "user_friends", "user_location", "user_likes");
        loginButton.setReadPermissions( permitions );
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ItemAdapter itemAdapter = new ItemAdapter();
    }

    public void initViews(){
        loginButton = (LoginButton) findViewById(R.id.login_button);
        searchButton = (Button) findViewById(R.id.search_button);
        searchEditText = (EditText) findViewById(R.id.search_EditText);
        numberOfResultsSpinner = (Spinner) findViewById(R.id.search_Spinner );



    }
}
