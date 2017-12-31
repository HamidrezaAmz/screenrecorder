package com.orpheusdroid.screenrecorder.viva;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orpheusdroid.screenrecorder.R;

/**
 * Created by Reza Amozadeh on 12/31/2017.
 */

public class VivaFragment extends Fragment {

    public VivaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.viva_fragment_layout, null);
        return v;
    }
}
