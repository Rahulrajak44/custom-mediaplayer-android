package org.videolan.vlc.gui;

import android.os.Bundle;
import android.widget.TextView;

import org.videolan.libvlc.util.VLCUtil;
import org.videolan.vlc.R;
import org.videolan.vlc.VLCApplication;
import org.videolan.vlc.config.Config;

public class CompatErrorActivity extends BaseActivity {
    public final static String TAG = "VLC/CompatErrorActivity";

    /**
     * Simple friendly activity to tell the user something's wrong.
     *
     * Intent parameters (all optional):
     * runtimeError (bool) - Set to true if you want to show a runtime error
     *                       (defaults to a compatibility error)
     * message (string) - the more detailed problem
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_compatible);
        TextView tvo = (TextView)findViewById(R.id.message);
        Config config = ((VLCApplication)getApplication()).getConfig();

        tvo.setText(getString(R.string.error_not_compatible, config.getAppName()));
        String errorMsg = VLCUtil.getErrorMsg();
        if(getIntent().getBooleanExtra("runtimeError", false))
            if(getIntent().getStringExtra("message") != null) {
                errorMsg = getIntent().getStringExtra("message");
                tvo.setText(getString(R.string.error_problem, config.getAppName()));
            }

        TextView tv = (TextView)findViewById(R.id.errormsg);
        tv.setText(getResources().getString(R.string.error_message_is) + "\n" + errorMsg);
    }
}
