package com.example.catherinemorris.shoppinwithfriends.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catherinemorris.shoppinwithfriends.R;

/**
 * Created by catherinemorris on 4/22/15.
 */
public class ScreenSlideFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_home_screen, container, false);
        return rootView;
    }
}
