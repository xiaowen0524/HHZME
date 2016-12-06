package hhzmy.bwei.com.hhzme.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import hhzmy.bwei.com.hhzme.R;

public class WebActivity extends AppCompatActivity {
    private WebView web;
    private TextView meijiduizhen;
    private ImageView mjdzFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mjdzFanhui = (ImageView) findViewById(R.id.mjdzFanhui);
        meijiduizhen = (TextView) findViewById(R.id.meijiduizhen);
        mjdzFanhui.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        web = (WebView) findViewById(R.id.webview);
        // 得到传值
        Intent intent = getIntent();

        String url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        meijiduizhen.setText(name);
        // 打开地址
        web.loadUrl(url);
        web.setWebChromeClient(new WebChromeClient());
    }
}
