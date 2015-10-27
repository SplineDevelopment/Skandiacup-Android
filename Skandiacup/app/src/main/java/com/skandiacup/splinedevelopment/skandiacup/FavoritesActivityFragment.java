package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jorgen on 27/10/15.
 */
public class FavoritesActivityFragment extends Fragment {
    public FavoritesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }
}
