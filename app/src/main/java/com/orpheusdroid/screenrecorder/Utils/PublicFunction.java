package com.orpheusdroid.screenrecorder.Utils;


import android.content.Context;
import android.graphics.Typeface;

public class PublicFunction {

    public PublicFunction() {

    }

    final static String TAG = PublicFunction.class.getSimpleName();

    static Typeface mTypeFace;

    //region typeface
    public static Typeface getTypeface() {
        return mTypeFace;
    }

    public static void setTypeface(Context context) {
        try {
            mTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //endregion

}
