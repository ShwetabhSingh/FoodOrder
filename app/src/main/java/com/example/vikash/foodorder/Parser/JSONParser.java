package com.example.vikash.foodorder.Parser;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.vikash.foodorder.Utils.StaticFields.url;


public class JSONParser {
  public static String resp;


  private static final String MAIN_URL = url;
  /**
   * TAGs Defined Here...
   */
  public static final String TAG = "TAG";

  private static Response response;
  /**
   * Get Data From WEB
   *
   * @return JSON Object
   */
  public static JSONObject getDataFromWeb() {
    try {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(MAIN_URL)
          .build();
      response = client.newCall(request).execute();
        resp = response.body().string();
        Log.e(TAG, "" + resp);
      return new JSONObject(response.body().string());
    } catch (@NonNull IOException | JSONException e) {
      Log.e(TAG, "" + e.getLocalizedMessage());


    }
    return null;
  }
}