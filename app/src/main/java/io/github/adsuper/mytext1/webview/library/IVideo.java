package io.github.adsuper.mytext1.webview.library;

import android.view.View;
import android.webkit.WebChromeClient;

/**
 * Created by cenxiaozhong on 2017/6/10.
 */

public interface IVideo {


    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);


    void onHideCustomView();


    boolean isVideoState();

}
