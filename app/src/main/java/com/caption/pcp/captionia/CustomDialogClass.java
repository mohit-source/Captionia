package com.caption.pcp.captionia;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by PCP on 29-12-2018.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {



    public Activity c;
    public Dialog d;
    public Button yes, no;
    public TextView username , email;
    public CircularImageView prof;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dailog);
        username = (TextView)findViewById(R.id.user_name);
        email = (TextView)findViewById(R.id.user_email);
        prof = (CircularImageView)findViewById(R.id.user_prof);

        User user = new User(getContext());

        username.setText(user.getUserName());
        email.setText(user.getEmail());
        prof.setBorderWidth(0);
        prof.addShadow();
        prof.setShadowRadius(0);
        Glide.with(getContext()).load(user.getUrl()).into(prof);



        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:



                                new User(getContext()).removeUser();

                                Intent intent=new Intent(getContext(),Login.class);
                                getContext().startActivity(intent);


                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
