package com.aegis.res.plugin.mywebjstest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
//        mWebView.loadUrl("https://maps.epgis.com/");

        mWebView.addJavascriptInterface(new AndroidtoJs(), "gps");
        mWebView.loadUrl("file:///android_asset/index2.html");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String writeResult = "javascript:showWriteResult('" + 1231111 + "')";
                mWebView.loadUrl(writeResult);
            }
        });


    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }


        public void onPageFinished(WebView view, String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            if (CookieStr != null) {
                Log.i("cookie", CookieStr);
            }
            super.onPageFinished(view, url);
        }


    }
}
