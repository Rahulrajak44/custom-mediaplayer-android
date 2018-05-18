package org.videolan.vlc.util;

import android.content.Context;
import android.util.Log;

import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.FullScreenBanner;
import com.appsgeyser.sdk.ads.IFullScreenBannerListener;
import com.appsgeyser.sdk.configuration.Constants;

/**
 * Created by q on 24.08.17.
 */

public class AdLoader {
    public static void loadFullscreenBanner(Context context, final ContentPlayAllowedListener playAllowedListener) {
        AppsgeyserSDK
                .getFullScreenBanner(context).setListener(new IFullScreenBannerListener() {
            @Override
            public void onLoadStarted() {
                Log.d("fullscreenBanner", "load started: " + System.currentTimeMillis());
            }

            @Override
            public void onLoadFinished(FullScreenBanner fullScreenBanner) {
                fullScreenBanner.show();
                Log.d("fullscreenBanner", "load finished: " + System.currentTimeMillis());
            }

            @Override
            public void onAdFailedToLoad(Context context, String s) {
                playAllowedListener.onPlayAllowed();
            }

            @Override
            public void onAdHided(Context context, String s) {
                playAllowedListener.onPlayAllowed();
            }
        });
        Log.d("fullscreenBanner", "load: " + System.currentTimeMillis());

        AppsgeyserSDK
                .getFullScreenBanner(context)
                .load(Constants.BannerLoadTags.ON_START);
    }

    public interface ContentPlayAllowedListener {
        void onPlayAllowed();
    }

}
