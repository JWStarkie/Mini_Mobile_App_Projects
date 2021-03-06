package testerson.testy.fragment_and_tabtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import testerson.testy.fragment_and_tabtest.Fragments.ConnectFragment;
import testerson.testy.fragment_and_tabtest.Fragments.ExploreFragment;
import testerson.testy.fragment_and_tabtest.Fragments.HomeFragment;
import testerson.testy.fragment_and_tabtest.Fragments.SectionPageAdapter;
import testerson.testy.fragment_and_tabtest.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    MenuItem prevMenuItem;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.container);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_explore:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_connect:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_settings:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                              @Override
                                              public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                              }

                                              @Override
                                              public void onPageSelected(int position) {
                                                  if (prevMenuItem != null) {
                                                      prevMenuItem.setChecked(false);
                                                  } else {
                                                      bottomNavigationView.getMenu().getItem(0).setChecked(false);
                                                  }
                                                  Log.d("page", "onPageSelected: " + position);
                                                  bottomNavigationView.getMenu().getItem(position).setChecked(true);
                                                  prevMenuItem = bottomNavigationView.getMenu().getItem(position);

                                              }

                                              @Override
                                              public void onPageScrollStateChanged(int state) {

                                              }
                                          });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        setupViewPager(viewPager);

//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new ExploreFragment(),"Explore");
        adapter.addFragment(new ConnectFragment(),"Connect");
        adapter.addFragment(new SettingsFragment(),"Settings");

        viewPager.setAdapter(adapter);


    }

}
