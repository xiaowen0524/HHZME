package hhzmy.bwei.com.hhzme.shangpinxiangqing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import hhzmy.bwei.com.hhzme.R;

public class ShouhouActivity extends Fragment {
    private WebView web;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_shouhou, null);
        web = (WebView) view.findViewById(R.id.webview);
        // 打开地址
        web.loadUrl("http://product.suning.com/pds-web/product/graphicSaleApp/0000000000/102295661.html");
        web.setWebChromeClient(new WebChromeClient());
        return view;
    }
}
