package io.github.adsuper.mytext1.ui;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.adsuper.mytext1.R;

/**
 *  scrollview 嵌套 webview 问题
 */
public class Main22Activity extends AppCompatActivity {

    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    @BindView(R.id.webview)
    WebView mWebview;

    private int rawy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        loadWebView();

        mWebview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float scaleY = view.getScaleY();
                Log.i("ScaleY::::", scaleY + "");

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        rawy = (int) motionEvent.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        int rawY2 = (int) motionEvent.getRawY();
                        int offsetY = rawY2 - rawy;

                        if(offsetY<=0){
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                        }

                        Log.i("ACTION_MOVE_mScrollView", offsetY + "");


                        break;
                }
                return false;
            }
        });
    }
    private String url = "http://www.jianshu.com/p/b6d682e301c2";

    private void loadWebView() {

        mWebview.loadUrl(url);




    }
}
