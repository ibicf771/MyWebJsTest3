package com.aegis.res.plugin.mywebjstest3;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by yangsimin on 2019/11/21.
 */

public class AndroidtoJs extends Object{
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void getLocation(String msg) {
        System.out.println("JS调用了Android的hello方法");
        Log.e("AndroidtoJs", msg);
    }
}
