package com.skandiacup.splinedevelopment.skandiacup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jorgen on 27/10/15.
 */
public class HomeActivityFragment extends Fragment {
    public HomeActivityFragment() {




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Fragment newsActivityFragment = new NewsActivityFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.child_fragment, newsActivityFragment).commit();


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
