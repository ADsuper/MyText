package io.github.adsuper.mytext1.swipebackhelper_text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by Mr.Jude on 2015/9/7.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝,不可滑动 1为敏感
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕,默认为任何地方可滑动
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(300);//activity联动时的偏移量。默认500px。

        //如果需要联动效果的话，联动的 两个 Activity 都需要初始化 SwipeBackHelper ，最好抽成一个 base，然后设置返回的 Activity 为不可滑动即可
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
        //ViewServer.get(this).removeWindow(this);
    }

    public void onResume() {
        super.onResume();
        //ViewServer.get(this).setFocusedWindow(this);
    }





}
