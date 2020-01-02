package com.caption.pcp.captionia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsertCaption extends AppCompatActivity {

    DatabaseReference databaseReference;
    Context context;
    String cat,caption;
    EditText cpt;
    ImageView post;
    Spinner spinnerProperty;
    public static final String Firebase_Server_URL = "https://captionia.firebaseio.com/";
    Firebase firebase;
    CoordinatorLayout coordinatorLayout;
    ImageView back;
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_caption);

          MobileAds.initialize(this,"ca-app-pub-7664588330662004~7774657865");
      //  MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        back= (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InsertCaption.this,Homepage.class);
                startActivity(i);
                finish();
            }
        });

        Firebase.setAndroidContext(InsertCaption.this);
        firebase = new Firebase(Firebase_Server_URL);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.cordinate);

        spinnerProperty = (Spinner) findViewById(R.id.spin);

        cpt = (EditText)findViewById(R.id.caption);
        post = (ImageView)findViewById(R.id.post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadCaption cap = new UploadCaption();

                data();
                databaseReference = FirebaseDatabase.getInstance().getReference(cat);
                // Adding name into class function object.
                if(!caption.isEmpty()   ) {

                    if(cat.equals("--Select Category--"))
                    {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Please select category!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                    else {
                        cap.setCaption_name(caption);
                        cap.setCategory_name(cat);

                        // Getting the ID from firebase database.
                        String StudentRecordIDFromServer = databaseReference.push().getKey();

                        // Adding the both name and number values using student details class object using ID.
                        databaseReference.child(StudentRecordIDFromServer).setValue(cap);

                        // Showing Toast message after successfully data submit.
                        //Toast.makeText(InsertCaption.this, "Caption Uploading...", Toast.LENGTH_SHORT).show();

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Your caption is added.", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        cpt.setText("");

                    }


                }
                else {

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Warning, Add Caption!!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference(InsertCategory.Database_Path);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                final List<String> propertyAddressList = new ArrayList<String>();

                for (DataSnapshot addressSnapshot: snapshot.getChildren()) {
                    String propertyAddress = addressSnapshot.child("insert").getValue(String.class);
                    if (propertyAddress!=null){
                        propertyAddressList.add(propertyAddress);
                    }
                }

                propertyAddressList.add(0,"--Select Category--");
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(InsertCaption.this, android.R.layout.simple_spinner_item, propertyAddressList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProperty.setAdapter(addressAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


        // Inflate the layout for this fragment



    }

    void data()
    {

        caption = cpt.getText().toString().trim();
        cat = String.valueOf(spinnerProperty.getSelectedItem());

    }


    @Override
    public void onBackPressed() {

        Intent i = new Intent(InsertCaption.this,Homepage.class);
        startActivity(i);
        finish();

    }
}
