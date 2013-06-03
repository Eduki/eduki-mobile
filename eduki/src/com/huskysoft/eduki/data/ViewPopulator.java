
package com.huskysoft.eduki.data;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.huskysoft.eduki.R;

/**
 * @author Cody Thomas ViewPopulator creates carousels based on given parameters
 *         such as layout
 */

public class ViewPopulator {

    private static final double INITIAL_ITEMS_COUNT = 3.7;

    /**
     * Takes each item and populates it into the carousel (of the parent), based
     * on the layout_id given, the onclick listener, the metrics of the
     * activity. If the list is empty, will display the message in a box.
     * 
     * @param items
     * @param parent
     * @param layout_id
     * @param v
     * @param act
     * @param message
     */
    public static <K> void populateCarousel(List<K> items, LinearLayout parent,
            int layout_id, View.OnClickListener v, Activity act, String message) {
        if (items.size() == 0) {
            populateCarouselEmpty(parent, act, message);
        } else {
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
    }

    /**
     * Populates an empty carousel
     * 
     * @param parent
     * @param act
     * @param message
     */
    public static <K> void populateCarouselEmpty(LinearLayout parent, Activity act, String message) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int imageWidth = (int) (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imageWidth,
                imageWidth);
        llp.setMargins(0, 5, 10, 5);
        TextView tv = (TextView) act.getLayoutInflater().inflate(R.layout.empty_carousel_item,
                null, false);
        tv.setText(message);
        tv.setLayoutParams(llp);
        parent.addView(tv);
    }

    /**
     * Populates the carousel based on the params, with "selected" being placed
     * first and as the state of selected
     * 
     * @param items
     * @param parent
     * @param layout_id
     * @param v
     * @param act
     * @param selected
     */
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
