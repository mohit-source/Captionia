package com.caption.pcp.captionia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  final User user =new User(Splash.this);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            /*    if(user.getEmail()!="")
                {


                    Intent i = new Intent(Splash.this, Homepage.class);
                    i.putExtra("email",user.getEmail());
                    startActivity(i);
                    finish();
                }

                else
                {

                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);
                    finish();
                }*/

                Intent i = new Intent(Splash.this, Homepage.class);
                startActivity(i);
                finish();

            }
        },3000);






    }
}
