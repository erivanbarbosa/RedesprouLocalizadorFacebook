package com.redesprou.redesproulocalizadorfacebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.facebook.GraphRequest.*;
import static com.facebook.internal.FacebookDialogFragment.TAG;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button searchButton;
    EditText searchEditText;
    Spinner numberOfResultsSpinner;
    public static CallbackManager callbackManager;
    RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<FacebookPageItem> facebookPageItems;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        facebookPageItems = new ArrayList<FacebookPageItem>();

        adapter = new ItemAdapter(facebookPageItems, this);
        recyclerView.setAdapter(adapter);

        //final ItemAdapter itemAdapter = new ItemAdapter();
        callbackManager = CallbackManager.Factory.create();

        initViews();
        final List<String> permitions = Arrays.asList("email", "public_profile", "user_friends", "user_location", "user_likes");
        loginButton.setReadPermissions(permitions);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Facebook login successful.");
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Logging in canceled.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Facebook login canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error occurred while logging in. Please try again.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Facebook login error.");
            }
        });

        context = this;
        final ArrayList<JSONObject> objectList = new ArrayList<JSONObject>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestsController.requestPlace(null, Integer.MAX_VALUE, 100, searchEditText.getText().toString(),
                        new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        for(int i = 0; i < array.length(); i++ )
                        {
                            try {
                                JSONObject object = null;
                                object = array.getJSONObject(i);

                                String nome = object.getString("name");
                                String descricao = object.getString("id");

                                adapter.addPage( new FacebookPageItem(nome, descricao));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, context );
            }


        });

    }

    public void initViews() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        searchButton = (Button) findViewById(R.id.search_button);
        searchEditText = (EditText) findViewById(R.id.search_EditText);
        numberOfResultsSpinner = (Spinner) findViewById(R.id.search_Spinner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public static void PlaceRequest(int resultLimit, String searchText, GraphJSONArrayCallback callback, Context context) {
        try {
            if (AccessToken.getCurrentAccessToken() != null) {
                GraphRequest request = newPlacesSearchRequest
                        (AccessToken.getCurrentAccessToken(), null, Integer.MAX_VALUE, resultLimit, searchText, callback);

                request.executeAsync();
            } else {
                Toast.makeText(context, "Por favor, logue primeiro!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Digite um valor válido!", Toast.LENGTH_LONG).show();
        }


    }


}
