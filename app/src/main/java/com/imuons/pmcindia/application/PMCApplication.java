package com.imuons.pmcindia.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class PMCApplication extends Application {
   public static ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        if(imageLoader==null){
            intImageLoader();
        }
    }

    private void intImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
           .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
           .defaultDisplayImageOptions(defaultOptions)
           .build();

       ImageLoader.getInstance().init(config);
       if (ImageLoader.getInstance().isInited()){
           imageLoader=imageLoader.getInstance();
       }
    }
}
