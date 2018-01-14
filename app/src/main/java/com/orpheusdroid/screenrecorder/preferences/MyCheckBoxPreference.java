package com.orpheusdroid.screenrecorder.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.orpheusdroid.screenrecorder.R;
import com.orpheusdroid.screenrecorder.Utils.PublicFunction;

/**
 * Created by Reza Amozadeh on 1/7/2018.
 */

public class MyCheckBoxPreference extends CheckBoxPreference {

    private Context context;

    public MyCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public MyCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public MyCheckBoxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyCheckBoxPreference(Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        TextView subtitleView = (TextView) view.findViewById(android.R.id.summary);

        titleView.setTypeface(PublicFunction.getTypeface());
        titleView.setTextColor(context.getColor(R.color.white));

        subtitleView.setTypeface(PublicFunction.getTypeface());
        subtitleView.setTextColor(context.getColor(R.color.gray));
    }
}
