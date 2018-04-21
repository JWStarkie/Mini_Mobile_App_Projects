package testerson.testy.new_tab_test.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import testerson.testy.new_tab_test.R;
import testerson.testy.new_tab_test.Tabs.PopularTab;
import testerson.testy.new_tab_test.Tabs.AllTab;
import testerson.testy.new_tab_test.Tabs.SortedTab;
import testerson.testy.new_tab_test.Tabs.TabsPagerAdapter;


/**
 * Created by Owner on 02/04/2018.
 */

public class ConnectFragment extends Fragment{

    private ViewPager viewPager;

    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.connect_tab,container,false);

            ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);
            setupViewPager(viewPager);

            TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new AllTab(), "All");
        adapter.addFragment(new PopularTab(), "Actor");
        adapter.addFragment(new SortedTab(), "Genre");

        viewPager.setAdapter(adapter);
    }

}
