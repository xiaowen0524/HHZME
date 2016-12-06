package hhzmy.bwei.com.hhzme.sousuoyemian;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5832ddc6");
		super.onCreate();
	}
}
