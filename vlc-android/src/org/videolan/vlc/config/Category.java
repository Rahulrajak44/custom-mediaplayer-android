package org.videolan.vlc.config;

import java.util.List;

/**
 * Created by q on 03.08.17.
 */

public class Category {

    private String name;
    private List<Channel> channelList;

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
