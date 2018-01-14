package com.orpheusdroid.screenrecorder.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.orpheusdroid.screenrecorder.R;
import com.orpheusdroid.screenrecorder.Utils.PublicFunction;

/**
 * Created by Reza Amozadeh on 1/7/2018.
 */

public class MyListPreference extends ListPreference {

    private Context context;

    public MyListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public MyListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public MyListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyListPreference(Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        titleView.setTypeface(PublicFunction.getTypeface());
        titleView.setTextColor(context.getColor(R.color.white));
    }
}
