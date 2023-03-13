package io.bckyrd.pmodule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SwitchActivity extends AppCompatActivity {

    DatabaseReference dref;
    TextView upper_state, lower_state;
    ImageView upper_img, lower_img;
    String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        // Add these two lines to show the title and the back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Switch");

        //compare the stations
        upper_state =findViewById(R.id.textView_one);
        lower_state =findViewById(R.id.textView_two);
        upper_img =findViewById(R.id.imageView_one);
        lower_img =findViewById(R.id.imageView_two);
        //
        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                status = dataSnapshot.child("station/station_one/").getValue().toString();
                //
                upper_state.setText(status);
                if(status.equals("station is on")){
                    upper_img.setImageResource(R.drawable.switch_on);
                }else{
                    upper_img.setImageResource(R.drawable.switch_off);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status = dataSnapshot.child("/station/station_two/").getValue().toString();
                //
                lower_state.setText(status);
                if(status.equals("station is on")){
                    lower_img.setImageResource(R.drawable.switch_on);
                }else{
                    lower_img.setImageResource(R.drawable.switch_off);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}





