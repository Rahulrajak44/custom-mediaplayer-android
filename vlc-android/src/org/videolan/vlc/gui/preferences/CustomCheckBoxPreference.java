package org.videolan.vlc.gui.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


/**
 * Created by roma on 31.07.2017.
 */

public class CustomCheckBoxPreference extends android.support.v7.preference.CheckBoxPreference {
    private Integer color;
    private CheckBox checkBox;

    @SuppressLint("NewApi")
    public CustomCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomCheckBoxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int color) {
        this.color = color;
        if(checkBox != null){
            applyColor();
        }
    }

    private void applyColor(){

        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {color, Color.argb(255, 170, 170, 170)};
        CompoundButtonCompat.setButtonTintList(checkBox, new ColorStateList(states, colors));
    }

/*    @Override
    protected void onBindView(View view) {

        super.onBindView(view);
        checkBox = findSwitchInChildviews((ViewGroup) view);
        if(color != null){
            applyColor();
        }
    }*/

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        checkBox = findSwitchInChildviews((ViewGroup) holder.itemView);
        if(color != null){
            applyColor();
        }
    }

    private CheckBox findSwitchInChildviews(ViewGroup view) {
        for (int i=0;i<view.getChildCount();i++) {
            View thisChildview = view.getChildAt(i);
            if (thisChildview instanceof CheckBox) {
                return (CheckBox)thisChildview;
            }
            else if (thisChildview instanceof  ViewGroup) {
                CheckBox theSwitch = findSwitchInChildviews((ViewGroup) thisChildview);
                if (theSwitch!=null) return theSwitch;
            }
        }
        return null;
    }
}