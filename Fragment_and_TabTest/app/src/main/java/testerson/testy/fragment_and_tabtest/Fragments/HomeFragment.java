package testerson.testy.fragment_and_tabtest.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import testerson.testy.fragment_and_tabtest.R;

/**
 * Created by Owner on 03/04/2018.
 */

public class HomeFragment extends Fragment {

    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_tab,container,false);

        activity = this.getActivity();



        return view;
    }
}
