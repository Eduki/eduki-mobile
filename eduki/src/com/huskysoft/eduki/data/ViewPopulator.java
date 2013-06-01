
package com.huskysoft.eduki.data;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ViewPopulator {

    private static final double INITIAL_ITEMS_COUNT = 3.7;

    public static <K> void populateCarousel(List<K> items, LinearLayout parent,
                int layout_id, View.OnClickListener v, Activity act) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int imageWidth = (int) (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imageWidth,
                imageWidth);
        llp.setMargins(0, 5, 10, 5);
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

    public static <K> void populateCarouselWithSelected(List<K> items, LinearLayout parent,
                int layout_id, View.OnClickListener v, Activity act, K selected) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int imageWidth = (int) (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imageWidth,
                imageWidth);
        llp.setMargins(0, 5, 10, 5);
        int i = 1;
        TextView tvFirst = (TextView) act.getLayoutInflater().inflate(
                layout_id, null, false);
        tvFirst.setId(0);
        tvFirst.setText(selected.toString());
        tvFirst.setSelected(true);
        tvFirst.setLayoutParams(llp);
        parent.addView(tvFirst);
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
        items.add(0, selected);
    }
}
