package com.orpheusdroid.screenrecorder.viva;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.orpheusdroid.screenrecorder.R;
import com.orpheusdroid.screenrecorder.SettingsPreferenceFragment;

public class VivaActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viva);

        frameLayout = (FrameLayout) findViewById(R.id.frame_container);
        addFragment(new SettingsPreferenceFragment());
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, "HELLO");
        fragmentTransaction.commit();
    }


}
