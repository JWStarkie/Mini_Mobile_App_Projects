package testerson.testy.new_tab_test.Tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import testerson.testy.new_tab_test.R;


/**
 * Created by Owner on 02/04/2018.
 */

public class AllTab extends Fragment{

    private ViewPager viewPager;

    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_layout,container,false);

        activity = this.getActivity();

        return view;
    }
}
