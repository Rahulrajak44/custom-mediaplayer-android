package org.videolan.vlc.gui.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by roma on 31.07.2017.
 */

public class CustomSwitchPreference extends android.support.v7.preference.SwitchPreferenceCompat {

    private Integer color;
    private SwitchCompat theSwitch;
    @SuppressLint("NewApi")
    public CustomSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwitchPreference(Context context, int color) {
        super(context);
        this.color = color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        theSwitch = findSwitchInChildviews((ViewGroup) holder.itemView);
        if(color != null){
            applyColor();
        }
    }

    private void applyColor(){

        int[][] states = new int[][] {
                new int[] {-android.R.attr.state_checked},
                new int[] {android.R.attr.state_checked},
        };

        int[] thumbColors = new int[] {
                Color.argb(255, 236, 236, 236),
                color,
        };

        int[] trackColors = new int[] {
                Color.argb(255, 0, 0, 0),
                color,
        };

        DrawableCompat.setTintList(DrawableCompat.wrap(theSwitch.getThumbDrawable()), new ColorStateList(states, thumbColors));
        DrawableCompat.setTintList(DrawableCompat.wrap(theSwitch.getTrackDrawable()), new ColorStateList(states, trackColors));
    }

    private SwitchCompat findSwitchInChildviews(ViewGroup view) {
        for (int i=0;i<view.getChildCount();i++) {
            View thisChildview = view.getChildAt(i);
            if (thisChildview instanceof SwitchCompat) {
                return (SwitchCompat)thisChildview;
            }
            else if (thisChildview instanceof  ViewGroup) {
                SwitchCompat theSwitch = findSwitchInChildviews((ViewGroup) thisChildview);
                if (theSwitch!=null) return theSwitch;
            }
        }
        return null;
    }
}