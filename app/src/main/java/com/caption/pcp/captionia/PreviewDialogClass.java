package com.caption.pcp.captionia;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

/**
 * Created by PCP on 29-12-2018.
 */

public class PreviewDialogClass extends Dialog implements
        View.OnClickListener {



    public Context c;
    public String cap;

    public TextView bio;
    ImageView cut;
    AdView adView;

    public PreviewDialogClass(Context a,String cap) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.cap = cap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preview_dailog);




        bio = (TextView)findViewById(R.id.bio);

        bio.setText(cap);
        cut = (ImageView)findViewById(R.id.cut);
        cut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cut:
                dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}
