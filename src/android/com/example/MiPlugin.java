/**
 */
package com.example;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;

import java.util.Date;

public class MiPlugin extends CordovaPlugin {
  private static final String TAG = "MiPlugin";
  
  public static class Prod {
    private String done;
    private String title;
    public Prod(String done, String title) {
      // this.done = done;
      // this.title = title;
    }
  }

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Inicializando MiPlugin");
  }
  
  public boolean execute(String action, String args, final CallbackContext callbackContext) throws JSONException {
    Log.d(TAG, "entro a funcion");
    if(action.equals("crear")) {
      //JSONObject jsonObject = new JSONObject(args);
      Log.d(TAG, "entro if crear" + args);
      // Write a message to the database
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference myRef = database.getReference("test");
      myRef.setValue(args);
      PluginResult result = new PluginResult(PluginResult.Status.OK);
      result.setKeepCallback(true);
      callbackContext.sendPluginResult(result);
    }if(action.equals("leer")) {
      Log.d(TAG, "entro if leer");
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference myRef = database.getReference();
      // Read from the database
      myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            // Prod prod = dataSnapshot.child("tasks").child("-L6HtnctZUXvbxuvCdAz").getValue(Prod.class);
            // System.out.println(prod);
            String value = dataSnapshot.child("tasks").child("-L6HtnctZUXvbxuvCdAz").child("title").getValue(String.class);
            Log.d(TAG, "Value is: " + value);
            System.out.println(value);
            //return value;
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
      });
    }
    return true;
  }

}
