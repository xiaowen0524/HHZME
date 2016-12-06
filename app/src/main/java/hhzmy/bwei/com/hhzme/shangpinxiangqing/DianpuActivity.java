package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import hhzmy.bwei.com.hhzme.R;

public class DianpuActivity extends AppCompatActivity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianpu);
        web = (WebView) findViewById(R.id.webview);
        // 打开地址
        web.loadUrl("http://shop.m.suning.com/app/30000332.html?client=app");
        web.setWebChromeClient(new WebChromeClient());
    }
}
