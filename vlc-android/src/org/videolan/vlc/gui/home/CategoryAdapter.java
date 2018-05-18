package org.videolan.vlc.gui.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.videolan.vlc.R;
import org.videolan.vlc.config.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by q on 03.08.17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<Category> categoryList;
    private Context context;
    private CategoryClickListener categoryClickListener;

    public CategoryAdapter(Context context) {
        this.categoryList = new ArrayList<>();
        this.context = context;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
    }

    public interface CategoryClickListener{
        void onClick(int position);
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.category_layout, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.title.setText(category.getName());
        ChannelAdapter channelAdapter = new ChannelAdapter(context);
        channelAdapter.setChannelList(category.getChannelList());
        holder.channelList.setAdapter(channelAdapter);
        holder.channelList.setHasFixedSize(true);
        holder.channelList.setNestedScrollingEnabled(false);
        holder.channelList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        if(categoryClickListener != null) {
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categoryClickListener.onClick(holder.getAdapterPosition());
                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView channelList;
        RelativeLayout relativeLayout;


        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            channelList = view.findViewById(R.id.channelList);
            relativeLayout = view.findViewById(R.id.titleFrame);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}