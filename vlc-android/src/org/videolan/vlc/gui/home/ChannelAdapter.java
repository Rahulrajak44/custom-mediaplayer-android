package org.videolan.vlc.gui.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.videolan.medialibrary.media.MediaWrapper;
import org.videolan.vlc.R;
import org.videolan.vlc.config.Channel;
import org.videolan.vlc.media.MediaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by q on 04.08.17.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {

    private final List<Channel> channelList;
    private Context context;

    public ChannelAdapter(Context context) {
        this.channelList = new ArrayList<>();
        this.context = context;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList.clear();
        this.channelList.addAll(channelList);
    }


    @Override
    public ChannelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.channel_layout, parent, false);

        return new ChannelAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ChannelAdapter.ViewHolder holder, int position) {
        Channel channel = channelList.get(position);
        holder.title.setText(channel.getName());
        holder.imageView.setImageDrawable(channel.getImage());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaWrapper mw = new MediaWrapper(Uri.parse(channelList.get(holder.getAdapterPosition()).getLink()));
                playMedia(mw);
            }
        });
    }


    public void playMedia(MediaWrapper mw) {
        mw.setType(MediaWrapper.TYPE_STREAM);
        MediaUtils.openMedia(context, mw);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;
        LinearLayout container;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.image);
            container = view.findViewById(R.id.container);
        }
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

}
