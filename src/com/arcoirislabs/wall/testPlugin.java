package com.arcoirislabs.wall;
import java.io.IOException;
import java.io.InputStream;

    import org.apache.cordova.api.Plugin;
    import org.apache.cordova.api.PluginResult;
    import org.apache.cordova.api.PluginResult.Status;
    import org.json.JSONArray;

    import android.app.WallpaperManager;
import android.content.Context;

    public class testPlugin extends Plugin {
        public final String ACTION_SET_WALLPAPER = "setWallPaper";
        @Override
        public PluginResult execute(String action, JSONArray arg1, String callbackId) {
            PluginResult result = new PluginResult(Status.INVALID_ACTION);
            if (action.equals(ACTION_SET_WALLPAPER)) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance((Context) this.ctx);
                try {
                  InputStream bitmap=null;
                   bitmap=ctx.getActivity().getAssets().open("www/img/" + arg1.getString(0));//reference to image folder
                    Bitmap bit=BitmapFactory.decodeStream(bitmap);
                    wallpaperManager.setBitmap(bit);
                    result = new PluginResult(Status.OK);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = new PluginResult(Status.ERROR, e.getMessage());
                }
            }
            return result;
        }
