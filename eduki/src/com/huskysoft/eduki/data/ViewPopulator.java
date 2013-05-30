package com.huskysoft.eduki.data;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.huskysoft.eduki.R;

public class ViewPopulator {

    private static final double INITIAL_ITEMS_COUNT = 3.5;
    
    public static <K> void populateCarousel(List<K> items, LinearLayout parent, 
                                        int layout_id, View.OnClickListener v, Activity act) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int imageWidth = (int) (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imageWidth,
                imageWidth);
        llp.setMargins(5, 5, 5, 5);
        int i = 0;
        for (K item : items) {
            TextView tv = (TextView) act.getLayoutInflater().inflate(
                    layout_id, null, false);
            tv.setText(item.toString());
            tv.setLayoutParams(llp);
            tv.setId(i);
            tv.setOnClickListener(v);
            i++;
            parent.addView(tv);
        }
    }
}
