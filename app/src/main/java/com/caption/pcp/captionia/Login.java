    package com.caption.pcp.captionia;

    import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

    public class Login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

        private LinearLayout Prof_Section;
        private SignInButton SignIn;

        private GoogleApiClient googleApiClient;
        private static final int REQ_CODE = 9001;
        private TextView privacy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        privacy=(TextView)findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(Login.this,PrivacyPolicy.class);
                    startActivity(i);
            }
        });


        SignIn = (SignInButton)findViewById(R.id.signin);

        SignIn.setOnClickListener(this);


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

    }


        @Override
        public void onClick(View v) {


            switch (v.getId())
            {

                    case R.id.signin:
                        signIn();
                        break;

            }

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }

        private   void signIn()
        {
            if(checkInternetConenction()) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, REQ_CODE);
            }
            else
            {
                Toast.makeText(this, "Check internet connection", Toast.LENGTH_SHORT).show();
            }

        }
        public void signOut()
        {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    updateUI(false);
                }
            });
        }
        private void handleResult(GoogleSignInResult result)
        {
            if(result.isSuccess())
            {
                GoogleSignInAccount account  = result.getSignInAccount();
                String name = account.getDisplayName();
                String email = account.getEmail();
                String img_url = account.getPhotoUrl().toString();

                updateUI(true);

                User user = new User(Login.this);
                user.setUserName(name);
                user.setEmail(email);
                user.setUrl(img_url);

                Intent i = new Intent(Login.this, Homepage.class);
                i.putExtra("name",name);
                i.putExtra("email",email);
                i.putExtra("image_url",img_url);
                startActivity(i);
                finish();

            }
            else
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        private void updateUI(Boolean isLogin)
        {

            if(isLogin)
            {

            }
            else
            {

            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==REQ_CODE)
            {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(result);
            }
        }





        private boolean checkInternetConenction()
        {
            // get Connectivity Manager object to check connection
            ConnectivityManager connec
                    =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

            // Check for network connections
            if ( connec.getNetworkInfo(0).getState() ==
                    android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() ==
                            android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() ==
                            android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED )
            {

                return true;
            }
            else if (
                    connec.getNetworkInfo(0).getState() ==
                            android.net.NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() ==
                                    android.net.NetworkInfo.State.DISCONNECTED  )
            {

                return false;
            }
            return false;
        }



        @Override
        public void onBackPressed() {

                super.onBackPressed();
                Intent i=new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            finish();

        }


    }
