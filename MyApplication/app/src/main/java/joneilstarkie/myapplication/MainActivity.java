package joneilstarkie.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import joneilstarkie.myapplication.fragments.ConnectFragment;
import joneilstarkie.myapplication.fragments.ExploreFragment;
import joneilstarkie.myapplication.fragments.HomeFragment;
import joneilstarkie.myapplication.fragments.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new ConnectFragment(),"Connect");
        adapter.addFragment(new ExploreFragment(),"Explore");
        adapter.addFragment(new HomeFragment(),"Home");

        viewPager.setAdapter(adapter);


    }



}
