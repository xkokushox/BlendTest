package com.appsorama.blendtest.util;

import android.content.res.Resources;
import android.util.TypedValue;

import com.appsorama.blendtest.BlendApplication;

/**
 * Created by Jose Torres in FreakyByte on 19/04/16.
 */
public class AndroidUtil {


    public static int dpToPx(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, BlendApplication.getInstance().getResources().getDisplayMetrics());
        return (int) px;
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
}
