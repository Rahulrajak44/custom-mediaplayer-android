package org.videolan.vlc.gui.tv;

import android.os.Bundle;
import android.widget.TextView;

import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.R;
import org.videolan.vlc.VLCApplication;
import org.videolan.vlc.config.Config;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.helpers.UiTools;

public class AboutActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);

        Config config = ((VLCApplication)getApplication()).getConfig();
        TextView textView = (TextView) findViewById(R.id.about_text);
        textView.setText(getString(R.string.about_text, config.getAppName(), BuildConfig.VERSION_NAME));
        UiTools.fillAboutView(getWindow().getDecorView().getRootView());
        TvUtil.applyOverscanMargin(this);
    }
}
