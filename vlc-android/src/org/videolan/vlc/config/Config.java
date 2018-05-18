package org.videolan.vlc.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.videolan.vlc.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by q on 03.08.17.
 */

public class Config {

    private Context context;
    private int colorPrimary;
    private int colorPrimaryDark;
    private int colorAccent;
    private int backgroundColor;
    private List<Category> categoryList;
    private Drawable backgroundImage;
    private JSONObject settings;
    private boolean deviceVideos;
    private boolean deviceAudios;
    private String appName;
    //------------------------------------------------------

    public Config(Context context) {
        this.context = context;

        try {
            settings = new JSONObject(loadJSONFromAsset());
            deviceVideos = settings.getBoolean("deviceVideos");
            deviceAudios = settings.getBoolean("deviceAudios");
            JSONObject themeColors = settings.getJSONObject("themeColors");
            colorPrimary = readColor(themeColors,"colorPrimary");
            colorPrimaryDark = readColor(themeColors,"colorPrimaryDark");
            colorAccent = readColor(themeColors,"colorAccent");

            backgroundColor = readColor(settings,"backgroundColor");
            backgroundImage = createDrawable(settings.getString("backgroundImage"));
            categoryList = loadCategoryList();

            appName = context.getString(R.string.app_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------

    private int readColor(JSONObject jsonTheme, String name) throws JSONException {
        String color = jsonTheme.getString(name);
        if (color == null || color.equals("")) {
            return 0;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        return Color.parseColor(color);
    }

    private List<Category> loadCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try {
            JSONArray categoriesJsonArray = settings.getJSONArray("categories");
            for (int i =0; i < categoriesJsonArray.length(); i++) {
                JSONObject jsonCategory = categoriesJsonArray.getJSONObject(i);

                Category category = new Category();
                category.setName(jsonCategory.getString("categoryName"));

                List<Channel> channelList = new ArrayList<>();
                JSONArray channelsJsonArray = jsonCategory.getJSONArray("categoryChannels");
                for (int j =0; j < channelsJsonArray.length(); j++) {
                    JSONObject jsonChannel = channelsJsonArray.getJSONObject(j);

                    Channel channel = new Channel();
                    channel.setName(jsonChannel.getString("channelTitle"));
                    channel.setLink(jsonChannel.getString("channelUrl"));
                    channel.setImage(createDrawable(jsonChannel.getString("channelCover")));
                    channelList.add(channel);
                }
                category.setChannelList(channelList);
                categoryList.add(category);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryList;
    }


    private Drawable createDrawable(String link) {
        if (!link.equals("")) {
            Bitmap b = null;
            try {
                b = BitmapFactory.decodeStream(context.getAssets().open(link));
                b.setDensity(Bitmap.DENSITY_NONE);
                return new BitmapDrawable(context.getResources(), b);
            } catch (FileNotFoundException e) {
                Log.d("Config", "Image " + link + " not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("settings.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public Drawable getBackgroundImage() {
        return backgroundImage;
    }

    public int getColorPrimary() {
        return colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public boolean isDeviceVideos() {
        return deviceVideos;
    }

    public boolean isDeviceAudios() {
        return deviceAudios;
    }

    public String getAppName() {
        return appName;
    }
}