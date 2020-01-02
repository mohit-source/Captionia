package com.caption.pcp.captionia;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertCategory extends AppCompatActivity {


    Button SubmitButton ;
    EditText categories,url;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://captionia.firebaseio.com/";

    // Declaring String variables to store name & phone number get from EditText.
    String Category_name , Image_url;

    Firebase firebase;

    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Categories";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_category);


        Firebase.setAndroidContext(InsertCategory.this);

        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        SubmitButton = (Button)findViewById(R.id.btn);

        categories = (EditText)findViewById(R.id.insert);
        url  = (EditText)findViewById(R.id.insertLink);





        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Category category = new Category();

                Category_name = categories.getText().toString().trim();
                Image_url = url.getText().toString().trim();

                // Adding name into class function object.
                if(!Category_name.isEmpty() && !Image_url.isEmpty() ) {
                    category.setInsert(Category_name);
                    category.setUrl(Image_url);


                    // Getting the ID from firebase database.
                    String StudentRecordIDFromServer = databaseReference.push().getKey();

                    // Adding the both name and number values using student details class object using ID.
                    databaseReference.child(StudentRecordIDFromServer).setValue(category);

                    // Showing Toast message after successfully data submit.
                    Snackbar.make(view, "Category Inserted", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    categories.setText("");
                    url.setText("");

                }
                else {
                    Snackbar.make(view, "Please Enter Category ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });





    }







}
