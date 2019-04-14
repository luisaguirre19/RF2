package com.restfam.luisaguirre.rf;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class menu extends AppCompatActivity {

    private ResideMenu item;
    private ResideMenu resideMenu;
    private Context mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ResideMenu resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.fondo);
        resideMenu.attachToActivity(this);

        String titles [] = {"Home", "Profile", "Calendar", "Settings"};
        int icon[] = {R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a};

        for (int i=0; i<titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            //item.setOnClickListener(v);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
        }


    }


}
