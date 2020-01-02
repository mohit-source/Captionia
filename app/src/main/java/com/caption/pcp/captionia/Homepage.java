package com.caption.pcp.captionia;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

//This interface would help in swiping views
public class Homepage extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    String TAG = "MainActivity.java";

    String popUpContents[];
    PopupWindow popupWindowLanguage;
    ImageView buttonShowDropDown;

    //This is our tablayout
    private TabLayout tabLayout;
    Toolbar toolbar;
    //This is our viewPager
    private ViewPager viewPager;
     FloatingActionButton fab;
    ImageView edit , rate;
    private CircularImageView Prof_Picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        List<String> languageList = new ArrayList<String>();
        languageList.add("English");
        languageList.add("हिंदी");
        languageList.add("मराठी");
        languageList.add("ગુજરાતી (Gujarati)");
        languageList.add("தமிழ் (Tamil)");
        languageList.add("ਪੰਜਾਬੀ (Panjabi)");


        // convert to simple array
        popUpContents = new String[languageList.size()];
        languageList.toArray(popUpContents);


        // initialize pop up window
        popupWindowLanguage = popupWindowDogs();


        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.language:
                        // show the list view as dropdown
                        popupWindowLanguage.showAsDropDown(v, -5, 0);
                        break;
                }
            }
        };

        // our button
        buttonShowDropDown = (ImageView) findViewById(R.id.language);
        buttonShowDropDown.setOnClickListener(handler);


     /*   User user = new User(Homepage.this);

        Prof_Picture = (CircularImageView)findViewById(R.id.prof_pic);

        Prof_Picture.setBorderWidth(0);

        Prof_Picture.addShadow();

        Prof_Picture.setShadowRadius(0);

        Glide.with(Homepage.this).load(user.getUrl()).into(Prof_Picture);


        Prof_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CustomDialogClass cdd=new CustomDialogClass(Homepage.this);
                cdd.show();
            }
        });  */
        edit=(ImageView)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepage.this,InsertCategory.class);
                startActivity(i);
            }
        });

        rate=(ImageView)findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  rateApp();
                Intent i = new Intent(Homepage.this,ImageCaptionUpload.class);
                startActivity(i);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Homepage.this, InsertCaption.class);
                startActivity(i);
            }
        });

        //Adding toolbar to the activity
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        tabLayout.addTab(tabLayout.newTab().setText("HashTag"));
        tabLayout.addTab(tabLayout.newTab().setText("Insta Bio"));
        //tabLayout.addTab(tabLayout.newTab().setText("Images"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF4081"));
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.colorAccent)
        );

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


    }


    public PopupWindow popupWindowDogs() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewLang = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewLang.setAdapter(langAdapter(popUpContents));

        // set the item click listener
        listViewLang.setOnItemClickListener(new LangDropdownOnItemClistner());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewLang);

        return popupWindow;
    }

    private ArrayAdapter<String> langAdapter(String dogsArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogsArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[0];

                // visual settings for the list item
                TextView listItem = new TextView(Homepage.this);

                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(18);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

        return adapter;
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }




    private int getColorForTab(int position) {

        switch (position) {
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    return getResources().getColor(R.color.colorPrimaryDark, getTheme());
                return getResources().getColor(R.color.colorPrimaryDark);
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    return getResources().getColor(R.color.DarkPurple, getTheme());
                return getResources().getColor(R.color.DarkPurple);

            default:
                return 0;
        }

    }

    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }


    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;

            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            Intent i=new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }

}