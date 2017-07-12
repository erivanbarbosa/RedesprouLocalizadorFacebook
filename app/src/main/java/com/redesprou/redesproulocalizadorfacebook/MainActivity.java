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
import com.facebook.places.PlaceManager;
import com.facebook.places.model.PlaceFields;
import com.facebook.places.model.PlaceSearchRequestParams;


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

                PlaceSearchRequestParams.Builder builder =
                        new PlaceSearchRequestParams.Builder();

                builder.setSearchText("Dança");
                builder.setDistance(1000); // 1,000 meter maximum distance.
                builder.setLimit(10);
                builder.addField(PlaceFields.NAME);
                builder.addField(PlaceFields.LOCATION);
                builder.addField(PlaceFields.PHONE);
                builder.addField(PlaceFields.CATEGORY_LIST);
                builder.addField(PlaceFields.ABOUT);
                builder.addField(PlaceFields.COVER);
                builder.addField(PlaceFields.IS_VERIFIED);
                builder.addField(PlaceFields.ENGAGEMENT);
                builder.addField(PlaceFields.OVERALL_STAR_RATING);

// Get the current location from LocationManager or FusedLocationProviderApi

                GraphRequest request =
                        PlaceManager.newPlaceSearchRequestForLocation(builder.build(), null);

                request.setCallback(new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {

                      JSONObject objeto = response.getJSONObject();

                        if( objeto != null ) {
                            JSONArray jsonArray = objeto.optJSONArray("data");

                            if( jsonArray != null ) {
                                for( int i = 0; i < jsonArray.length(); i++ ) {

                                    try {
                                        JSONObject item = jsonArray.getJSONObject(i);

                                        String nome = item.getString("name");
                                        String id = item.getString("id");
                                        String telefone = item.optString("phone");
                                        String local = item.optString("location");
                                        String categoria = item.optString("category_list");
                                        String descricao = item.optString("about");
                                        String capa = item.optString("cover");
                                        boolean verificada = item.optBoolean("is_verified");
                                        String engajamento = item.optString("engagement");
                                        Double media = item.optDouble("overall_star_rating");


/*
                                    System.out.println( item.getString("name"));
                                    System.out.println( item.getString("id"));
                                    System.out.println( item.getString("phone"));
                                    System.out.println( item.getString("location"));
                                    System.out.println( item.getString("category_list"));
                                    System.out.println( item.getString("about"));
                                    System.out.println( item.getString("cover"));
                                    System.out.println( item.getBoolean("is_verified"));
                                    System.out.println( item.getString("engagement"));
                                    System.out.println( item.getDouble("overall_star_rating"));

                                    System.out.println( );
                                    System.out.println( "======================================");

                                    Iterator<String> string = item.keys();

                                    while ( string.hasNext() ) {
                                        System.out.println( string.next() );
                                    } */

                                        //facebookPageItems.add( new FacebookPageItem(nome, id, telefone, local))
                                        adapter.addPage(new FacebookPageItem(nome, id, telefone, local, categoria, descricao,
                                                capa, verificada, engajamento, media ));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                });

                request.executeAsync();
                System.out.println("Método");
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
