package com.aegis.res.plugin.mywebjstest3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;


public class MainActivity extends AppCompatActivity {

//    private WebView mWebView;
    private BridgeWebView bridgeWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bridgeWebView = findViewById(R.id.web_view);

        bridgeWebView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String msg = "默认接收到js的数据：" + data;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

                function.onCallBack("java默认接收完毕，并回传数据给js"); //回传数据给js
            }
        });

        bridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String msg = "指定接收到js的数据：" + data;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

                function.onCallBack("java指定接收完毕，并回传数据给js"); //回传数据给js
            }
        });

        bridgeWebView.loadUrl("file:///android_asset/index2.html");

        findViewById(R.id.JavaToJsDefault).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bridgeWebView.send("发送数据给js默认接收", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) { //处理js回传的数据
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        findViewById(R.id.JavaToJsSpec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bridgeWebView.callHandler("functionInJs", "发送数据给js指定接收", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) { //处理js回传的数据
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
