package io.bckyrd.pmodule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DistributeActivity extends AppCompatActivity {
    //nodes are same as connected clients im using them interchangeably.
    String path = "nodes/";
    //logs in the terminal during testing
    private static final String TAG = "Distribute";
    //firebase
    private DatabaseReference rootDatabaseref;
    //components
    TextView client_one,client_two,client_three,client_four;
    SwitchCompat switch_one,switch_two,switch_three,switch_four;
    //shared preferences
    private static String MY_PREFS ="switch_pref";
    private static String CLIENT_STATUS ="client_on";
    private static String SWITCH_STATUS ="switch_status";
    boolean switch_status;
    boolean client_status;
    SharedPreferences myPreferences;
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribute);

        // Add these two lines to show the title and the back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Distribute");

        //id to component
        client_one = findViewById(R.id.client_one);
        switch_one = findViewById(R.id.switch_one);
        client_two = findViewById(R.id.client_two);
        switch_two = findViewById(R.id.switch_two);
        client_three = findViewById(R.id.client_three);
        switch_three = findViewById(R.id.switch_three);
        client_four = findViewById(R.id.client_four);
        switch_four = findViewById(R.id.switch_four);
        //objects preferences
        myPreferences = getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(MY_PREFS,MODE_PRIVATE).edit();
        //default preferences
        switch_status = myPreferences.getBoolean(SWITCH_STATUS,false);
        client_status =myPreferences.getBoolean(CLIENT_STATUS,false);

        //on checked method - node same as client
        toggleNode();
    }

    private void toggleNode() {
        switch_one.setChecked(switch_status);
        switch_one.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            rootDatabaseref = FirebaseDatabase.getInstance().getReference().child(path+"node_one");
            if(compoundButton.isChecked()){
                //LOG CAT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, true);
                myEditor.putBoolean(CLIENT_STATUS,true);
                myEditor.apply();
                switch_one.setChecked(true);
                //DEBUG
                client_status =true;
                Log.d(TAG, "STATE CHECKED > ON"+ true);
                String state = "true";
                rootDatabaseref.setValue(state);
            }else {
                //NOT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, false);
                myEditor.putBoolean(CLIENT_STATUS,false);
                myEditor.apply();
                switch_one.setChecked(false);
                client_status =false;
                Log.d(TAG, "STATE CHECKED > OFF"+ false);
                String state = "false";
                rootDatabaseref.setValue(state);
            }
        });

        //SWITCH TWO
        switch_two.setChecked(switch_status);
        switch_two.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            rootDatabaseref = FirebaseDatabase.getInstance().getReference().child(path+"node_two");
            if(compoundButton.isChecked()){
                //LOG CAT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, true);
                myEditor.putBoolean(CLIENT_STATUS,true);
                myEditor.apply();
                switch_two.setChecked(true);
                //DEBUG
                client_status =true;
                Log.d(TAG, "STATE CHECKED > ON"+ true);
                String state = "true";
                rootDatabaseref.setValue(state);
            }else {
                //NOT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, false);
                myEditor.putBoolean(CLIENT_STATUS,false);
                myEditor.apply();
                switch_two.setChecked(false);
                client_status =false;
                Log.d(TAG, "STATE CHECKED > OFF"+ false);
                String state = "false";
                rootDatabaseref.setValue(state);
            }
        });

        //SWITCH THREE
        switch_three.setChecked(switch_status);
        switch_three.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            rootDatabaseref = FirebaseDatabase.getInstance().getReference().child(path+"node_three");
            if(compoundButton.isChecked()){
                //LOG CAT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, true);
                myEditor.putBoolean(CLIENT_STATUS,true);
                myEditor.apply();
                switch_three.setChecked(true);
                //DEBUG
                client_status =true;
                Log.d(TAG, "STATE CHECKED > ON"+ true);
                String state = "true";
                rootDatabaseref.setValue(state);
            }else {
                //NOT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, false);
                myEditor.putBoolean(CLIENT_STATUS,false);
                myEditor.apply();
                switch_three.setChecked(false);
                client_status =false;
                Log.d(TAG, "STATE CHECKED > OFF"+ false);
                String state = "false";
                rootDatabaseref.setValue(state);
            }
        });

        //SWITCH FOUR
        switch_four.setChecked(switch_status);
        switch_four.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            rootDatabaseref = FirebaseDatabase.getInstance().getReference().child(path+"node_four");
            if(compoundButton.isChecked()){
                //LOG CAT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, true);
                myEditor.putBoolean(CLIENT_STATUS,true);
                myEditor.apply();
                switch_four.setChecked(true);
                //DEBUG
                client_status =true;
                Log.d(TAG, "STATE CHECKED > ON"+ true);
                String state = "true";
                rootDatabaseref.setValue(state);
            }else {
                //NOT CHECKED
                myEditor.putBoolean(SWITCH_STATUS, false);
                myEditor.putBoolean(CLIENT_STATUS,false);
                myEditor.apply();
                switch_four.setChecked(false);
                client_status =false;
                Log.d(TAG, "STATE CHECKED > OFF"+ false);
                String state = "false";
                rootDatabaseref.setValue(state);
            }
        });
    }
}





