package org.videolan.vlc.config;

import android.graphics.drawable.Drawable;

/**
 * Created by q on 03.08.17.
 */

public class Channel {

    private String name;
    private Drawable image;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
