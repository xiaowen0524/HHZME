package hhzmy.bwei.com.hhzme.app;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by asus on 2016/11/9.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5832e341");
        super.onCreate();
        init();
    }

    public void init() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
        imageLoader.init(builder.build());
    }
}
