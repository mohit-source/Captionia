package com.caption.pcp.captionia;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PCP on 31-12-2017.
 */

public class User {

    private String url;
    private String name;
    private String email;



    Context context;
    SharedPreferences sharedPreferences;

    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }


    public String getUserName() {
        name=sharedPreferences.getString("usernamedata","");
        return name;
    }
    public void setUserName(String name) {
        this.name =name;
        sharedPreferences.edit().putString("usernamedata",name).commit();
    }

    public String getEmail() {
        email=sharedPreferences.getString("useremaildata","");
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
        sharedPreferences.edit().putString("useremaildata",email).commit();
    }

    public String getUrl()
    {
        url = sharedPreferences.getString("user_image_url","");
        return url;
    }
    public  void setUrl(String url)
    {
        this.url=url;
        sharedPreferences.edit().putString("user_image_url",url).commit();
    }




    public User(Context context)
    {
            this.context=context;
            sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }
}