package com.caption.pcp.captionia;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by PCP on 30-12-2018.
 */

public class LangDropdownOnItemClistner implements AdapterView.OnItemClickListener {

    Context mContext;
    String TAG = "DogsDropdownOnItemClickListener.java";

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

        // get the context and main activity to access variables
         mContext = v.getContext();
        Homepage mainActivity = ((Homepage) mContext);

        // add some animation when a list item was clicked
        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(10);
        v.startAnimation(fadeInAnimation);

        // dismiss the pop up
        mainActivity.popupWindowLanguage.dismiss();

        // get the id
        String selectedItemTag = ((TextView) v).getTag().toString();
        if(selectedItemTag == "English")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://image.winudf.com/v2/image/YnVpbGRwcm9qZWN0LmVuZ2xpc2hzdGF0dXNfaWNvbl8xNTMxNTg1NTI3XzA1NA/icon.png?w=170&fakeurl=1&type=.png");
            mContext.startActivity(i);
        }
        else  if(selectedItemTag=="ਪੰਜਾਬੀ (Panjabi)")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://imgd.androidappsapk.co/115/a/6/0/myapp.statusgraphy.punjabistatus.png");
            mContext.startActivity(i);
        }
       else if(selectedItemTag=="தமிழ் (Tamil)")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://imga.apk.tools/150/a/1/2/myapp.statusgraphy.tamilstatus.png");
            mContext.startActivity(i);
        }
      else  if(selectedItemTag=="ગુજરાતી (Gujarati)")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://image.winudf.com/v2/image/amF5dWpheXUuZ3VqYXJhdGkub25seS5ndWpzdGFfaWNvbl8xNTI3Njc1ODYzXzA5NQ/icon.png?w=170&fakeurl=1&type=.png");
            mContext.startActivity(i);
        }
       else if(selectedItemTag=="मराठी")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://cdn6.aptoide.com/imgs/c/e/5/ce5efbb949def6d2b6b492820fe8c8af_icon.png?w=120");
            mContext.startActivity(i);
        }
     else   if(selectedItemTag=="हिंदी")
        {
            Intent i = new Intent(mContext, Captions.class);
            i.putExtra("category",selectedItemTag);
            i.putExtra("head","https://cdn6.aptoide.com/imgs/d/6/7/d674ebeb06c93a3afc9ab5c53897488e_icon.png?w=120");
            mContext.startActivity(i);
        }

    }


}
