package com.caption.pcp.captionia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import java.util.Collections;
import java.util.List;

public class Captions extends AppCompatActivity {

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;
    String name,url;

    List<UploadCaption> list  = new ArrayList<>();
    public static final String Firebase_Server_URL = "https://captionia.firebaseio.com/";
    Firebase firebase;
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter ;

    TextView capt;
    ImageView icon;
    ImageView back;

    AdView adView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captions);

        MobileAds.initialize(this,"ca-app-pub-7664588330662004~7774657865");
       // MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        capt=(TextView)findViewById(R.id.capt);
        icon=(ImageView)findViewById(R.id.icn);

        Firebase.setAndroidContext(Captions.this);
        firebase = new Firebase(Firebase_Server_URL);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(Captions.this));


        progressDialog = new ProgressDialog(Captions.this);

        progressDialog.setMessage("Loading");

        progressDialog.show();

        Intent i = getIntent();
        url=i.getStringExtra("head");
        name=i.getStringExtra("category");


        capt.setText(name);
        Glide.with(Captions.this).load(url).into(icon);

        back= (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Captions.this,Homepage.class);
                startActivity(i);
                finish();
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference(name);
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    UploadCaption uploadCaption = dataSnapshot.getValue(UploadCaption.class);

                    list.add(uploadCaption);
                }

                adapter = new CaptionsRecyclerViewAdapter(Captions.this, list);
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

    /*    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                    final int fromPos = viewHolder.getAdapterPosition();
//                    final int toPos = viewHolder.getAdapterPosition();
//                    // move item in `fromPos` to `toPos` in adapter.
                return true;// true if moved, false otherwise
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
               // adapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                Toast.makeText(Captions.this, "Swiped", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);    */
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(Captions.this,Homepage.class);
        startActivity(i);
        finish();

    }

}
