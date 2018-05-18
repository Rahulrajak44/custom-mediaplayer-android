package org.videolan.vlc.gui.home;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.appsgeyser.sdk.ads.AdView;

import org.videolan.vlc.R;
import org.videolan.vlc.VLCApplication;
import org.videolan.vlc.config.Category;
import org.videolan.vlc.config.Config;
import org.videolan.vlc.gui.ContentActivity;

/**
 * Created by q on 03.08.17.
 */

public class ChannelGridActivity extends ContentActivity {

    private RecyclerView recyclerView;
    private RelativeLayout root;
    private Config config;
    private Category category;
    private ChannelAdapter categoryAdapter;
    private AdView adView;

    @Override
    public void onStart() {
        super.onStart();
        updateTitle();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channels_grid_fragment);

        initAudioPlayerContainerActivity();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adView = (AdView) findViewById(R.id.adView);

        int categoryId = getIntent().getIntExtra("category_id", 0);
        config = ((VLCApplication)getApplication()).getConfig();

        category = config.getCategoryList().get(categoryId);

        recyclerView = (RecyclerView) findViewById(R.id.channel_grid);
        root = (RelativeLayout) findViewById(R.id.root);
        if (config.getBackgroundImage() != null) {
            root.setBackground(config.getBackgroundImage());
        } else {
            root.setBackgroundColor(config.getBackgroundColor());
        }
        categoryAdapter = new ChannelAdapter(this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
        categoryAdapter.setChannelList(category.getChannelList());
        categoryAdapter.notifyDataSetChanged();
        updateTitle();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
    }

    public void updateTitle() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(category.getName());
            supportInvalidateOptionsMenu();
        }
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 126);
        return noOfColumns;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.onResume();//into onResume()
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null) {
            adView.onPause();//into onPause()
        }
    }
}
