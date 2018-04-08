package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by apple on 2018/3/5.
 */

public class FiveWebViewActivity extends AppCompatActivity {

    private WebView wv_view;
    private static final String UTF_8 = "UTF-8";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_webview);
        wv_view = findViewById(R.id.wv_view);

        WebSettings webSettings = wv_view.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName(UTF_8);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        wv_view.setWebViewClient(new MyWebViewClient());
        wv_view.setWebChromeClient(new WebChromeClient());

        wv_view.loadUrl("http://ypcyzx.shcloudvalley.com/entreship/web/page/login");
//        wv_view.loadUrl("http://www.baidu.com");

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            String currentUrl = wv_view.getUrl();
//            view.loadUrl(currentUrl);
//                    wv_view.loadUrl("http://ypcyzx.shcloudvalley.com/entreship/web/page/login");
            view.loadUrl("http://www.baidu.com");
            return false;
        }
    }
}
