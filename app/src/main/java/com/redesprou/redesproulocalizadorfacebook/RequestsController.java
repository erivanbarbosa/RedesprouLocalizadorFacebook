package com.redesprou.redesproulocalizadorfacebook;

import android.content.Context;
import android.location.Location;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

/**
 * Created by Erivan on 09/07/2017.
 */

public class RequestsController {


    public static void requestPlace(Location location, int distanceRadius, int resultLimits, String searchText,
                                    GraphRequest.GraphJSONArrayCallback callback, Context context){

        GraphRequest request = GraphRequest.newPlacesSearchRequest(AccessToken.getCurrentAccessToken(),
                null, distanceRadius, resultLimits, searchText, callback);

        request.executeAsync();
    }
}
