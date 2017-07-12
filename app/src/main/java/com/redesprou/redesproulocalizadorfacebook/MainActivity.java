package com.redesprou.redesproulocalizadorfacebook;


import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.places.PlaceManager;
import com.facebook.places.model.PlaceFields;
import com.facebook.places.model.PlaceSearchRequestParams;
import com.redesprou.redesproulocalizadorfacebook.util.PlaceFieldsUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        facebookPageItems = new ArrayList<FacebookPageItem>();
        adapter = new ItemAdapter(facebookPageItems, this);
        recyclerView.setAdapter(adapter);
        callbackManager = CallbackManager.Factory.create();


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                adapter.removeAll();
                String searchText = searchEditText.getText().toString();

                PlaceSearchRequestParams.Builder builder =
                        new PlaceSearchRequestParams.Builder();

                builder.setSearchText(searchText);
                builder.setDistance(1000);
                builder.setLimit(50);
                builder.addField(PlaceFields.NAME);
                builder.addField(PlaceFields.LOCATION);
                builder.addField(PlaceFields.PHONE);
                builder.addField(PlaceFields.CATEGORY_LIST);
                builder.addField(PlaceFields.ABOUT);
                builder.addField(PlaceFields.COVER);
                builder.addField(PlaceFields.IS_VERIFIED);
                builder.addField(PlaceFields.ENGAGEMENT);
                builder.addField(PlaceFields.OVERALL_STAR_RATING);

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

                                        String cover = "";
                                        JSONObject coverPhotoJson = item.getJSONObject("cover");
                                        if( coverPhotoJson != null ) {
                                            cover = coverPhotoJson.optString("source");

                                            System.out.println( "Source:   " + cover );
                                        }

                                        String categorias = "";
                                        JSONArray categoriasJsonArray = item.getJSONArray("category_list");
                                        if( categoriasJsonArray != null && categoriasJsonArray.length() > 0 )
                                        {
                                            for(int j = 0; j < categoriasJsonArray.length(); j++ ) {
                                                JSONObject categoriasJson = categoriasJsonArray.getJSONObject(j);

                                                if( categoriasJson != null ) {
                                                    categorias += categorias + ", " + categoriasJson.optString("name");
                                                }

                                            }
                                        }

                                        String engajamento = "";
                                        JSONObject engajamentoJson = item.getJSONObject("engagement");
                                        engajamento = "Visitors: " + engajamentoJson.optString("count") + "     -    " + engajamentoJson.optString("social_sentence");

                                        String telefone = item.optString("phone");
                                        if( !telefone.isEmpty() ) {
                                            String valor = "Phone: " + telefone.substring(1, 3) +  telefone.substring(4);
                                            telefone = valor;
                                        }

                                        String endereco = "";
                                        JSONObject enderecoJson = item.getJSONObject("location");
                                        endereco = "Address: " + enderecoJson.optString("street") + " - " + enderecoJson.optString("zip") + " - "
                                                + enderecoJson.optString("city") + " - " + enderecoJson.optString("country");


                                        String nome = item.getString("name");
                                        String id = item.getString("id");
                                        String descricao = "Description: " + item.optString("about");
                                        boolean verificada = item.optBoolean("is_verified");
                                        Double media = item.optDouble("overall_star_rating");

                                        adapter.addPage(new FacebookPageItem(nome, id, telefone, endereco, PlaceFieldsUtil.tratarCategorias(categorias), descricao,
                                                cover, verificada, engajamento, media ));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                });

                request.executeAsync();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initViews() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        searchButton = (Button) findViewById(R.id.search_button);
        searchEditText = (EditText) findViewById(R.id.search_EditText);
        numberOfResultsSpinner = (Spinner) findViewById(R.id.search_Spinner);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
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
