package io.github.adsuper.mytext1.webview.library;

/**
 * Created by cenxiaozhong on 2017/6/21.
 */

public interface DownLoadResultListener {


    void success(String path);

    void error(String path, String resUrl, String cause, Throwable e);

}
