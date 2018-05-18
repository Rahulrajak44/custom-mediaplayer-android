package org.videolan.vlc.gui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.videolan.vlc.R;
import org.videolan.vlc.VLCApplication;
import org.videolan.vlc.config.Config;
import org.videolan.vlc.gui.browser.MediaBrowserFragment;

/**
 * Created by q on 03.08.17.
 */

public class HomeFragment extends MediaBrowserFragment {

    private RecyclerView recyclerView;
    private FrameLayout root;
    private Config config;

    @Override
    public void onStart() {
        super.onStart();
        updateTitle();
        setFabPlayVisibility(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.channels_fragment, container, false);

        config = ((VLCApplication) getActivity().getApplication()).getConfig();

        recyclerView = v.findViewById(R.id.categoryList);
        root = v.findViewById(R.id.root);
        if (config.getBackgroundImage() != null) {
            root.setBackground(config.getBackgroundImage());
        } else {
            root.setBackgroundColor(config.getBackgroundColor());
        }
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity());
        categoryAdapter.setCategoryClickListener(new CategoryAdapter.CategoryClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ChannelGridActivity.class);
                intent.putExtra("category_id", position);

                getActivity().startActivity(intent);
            }
        });
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryAdapter.setCategoryList(config.getCategoryList());
        categoryAdapter.notifyDataSetChanged();
        mFabPlay=null;
        return v;
    }

    public void updateTitle() {
        final AppCompatActivity activity = (AppCompatActivity)getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(getTitle());
            activity.supportInvalidateOptionsMenu();
        }
    }

    public String getTitle() {
        return getString(R.string.home);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(false);
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
