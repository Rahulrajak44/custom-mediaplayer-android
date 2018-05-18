package org.videolan.vlc.gui;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;
import android.view.WindowManager;

import com.appsgeyser.sdk.AppsgeyserSDK;

import org.videolan.vlc.VLCApplication;
import org.videolan.vlc.config.Config;


public class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setDefaultNightMode(PreferenceManager.getDefaultSharedPreferences(VLCApplication.getAppContext()).getBoolean("daynight", false) ? AppCompatDelegate.MODE_NIGHT_AUTO : AppCompatDelegate.MODE_NIGHT_NO);
    }

    protected SharedPreferences mSettings;
    protected Config config;

    @Override
    protected void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /* Get settings */
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        /* Theme must be applied before super.onCreate */
        applyTheme();
        config = ((VLCApplication)getApplication()).getConfig();

        super.onCreate(savedInstanceState);
    }

    private void applyTheme() {
        Config config = ((VLCApplication)getApplication()).getConfig();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(config.getColorPrimaryDark());
        };
    }
}
