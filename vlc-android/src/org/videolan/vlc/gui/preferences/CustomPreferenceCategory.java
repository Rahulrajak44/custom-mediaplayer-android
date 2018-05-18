package org.videolan.vlc.gui.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by q on 05.08.17.
 */

public class CustomPreferenceCategory extends android.support.v7.preference.PreferenceCategory {
    private Integer color;
    private AppCompatTextView textView;

    @SuppressLint("NewApi")
    public CustomPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int color) {
        this.color = color;
        if(textView != null){
            applyColor();
        }
    }

    private void applyColor(){

        textView.setTextColor(color);
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
        textView = (AppCompatTextView) holder.itemView;
        if(color != null){
            applyColor();
        }
    }
}