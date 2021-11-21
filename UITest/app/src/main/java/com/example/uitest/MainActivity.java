package com.example.uitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = (MeowBottomNavigation) findViewById(R.id.bottom_nav);
        bottom_nav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottom_nav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_donut_large_24));
        bottom_nav.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_message_24));
        bottom_nav.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_supervised_user_circle_24));

        bottom_nav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                
            }
        });

        bottom_nav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getId()){
                    case 1:
                        transaction.replace(R.id.frameLayout, new HomeFragment()).commit();
                        break;
                    case 2:
                        transaction.replace(R.id.frameLayout, new StaticFragment()).commit();
                        break;
                    case 3:
                        transaction.replace(R.id.frameLayout, new MessageFragment()).commit();
                        break;
                    case 4:
                        transaction.replace(R.id.frameLayout, new ProfileFragment()).commit();
                        break;
                }
            }
        });

        bottom_nav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "already in pages", Toast.LENGTH_SHORT).show();
            }
        });

        bottom_nav.show(1, true);

    }
}