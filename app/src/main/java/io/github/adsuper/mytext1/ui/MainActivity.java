package io.github.adsuper.mytext1.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.adsuper.mytext1.R;
import io.github.adsuper.mytext1.statebarandnavigationbar.StatesBarAndNavigationBarActivity;
import io.github.adsuper.mytext1.swipebackhelper_text.BaseActivity;
import io.github.adsuper.mytext1.swipebackhelper_text.SwipeBackLayoutActivity;
import io.github.adsuper.mytext1.webview.WebViewActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * retrofit 配置 OkHttpClient 参考：http://wuxiaolong.me/2016/06/18/retrofits/
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.imageview)
    ImageView mImageview;

    int drawableResouce = R.drawable.guide_bg_me;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn4)
    Button mBtn4;
    private int lastX;
    private int lastY;
    private int rawy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        SwipeBackHelper.getCurrentPage(this)
//                .setSwipeBackEnable(false);
//        SwipeBackHelper.getCurrentPage(this).setDisallowInterceptTouchEvent(true);

//        RetrofitManager.getInstance().getDataFromServer_rxjava();
//        RetrofitManager.getInstance().getDataFromServer_retrofit();
//        RxjavaGuanChaZheMoShi.dingyue();
//        setImageview();
//        RetrofitManager.getInstance().getDataFromServer_rxjava_flatmap();
//        setImageview_map();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用


        mImageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rawy = (int) motionEvent.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        int rawY2 = (int) motionEvent.getRawY();
                        int offsetY = rawY2 - rawy;
                        Log.i("ACTION_MOVE_mImageview", offsetY + "");

                        break;
                    case MotionEvent.ACTION_UP:

                        int rawY1 = (int) motionEvent.getRawY();
                        Log.i("ACTION_UP_mImageview", (rawY1 - rawy) + "");

                        break;
                }


                return true;
            }
        });
    }

    /**
     * rxjava 线程变换示例，加载资源的过程在 io 线程 ，更新 ui 的线程在主线程
     */
    private void setImageview() {

        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
                //耗时操作
                Drawable drawable = getTheme().getDrawable(drawableResouce);
                e.onNext(drawable);
            }
        })
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Drawable value) {
                        mImageview.setImageDrawable(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * rxjava map 操作符
     */
    private void setImageview_map() {

        Observable.just(drawableResouce)
                .map(new Function<Integer, Drawable>() {
                    @Override
                    public Drawable apply(Integer integer) throws Exception {
                        // 将 integer 转化为 Drawable
                        return getTheme().getDrawable(drawableResouce);
                    }
                })
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Drawable value) {
                        mImageview.setImageDrawable(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.btn1:
                Intent intent = new Intent(this, SwipeBackLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(this, CollapsingToolbarLayoutActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(this, StatesBarAndNavigationBarActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(this, WebViewActivity.class);
                startActivity(intent4);
                break;

        }


    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                rawy = (int)event.getRawY();
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                int rawY2 = (int)event.getRawY();
//                int offsetY = rawY2 - rawy;
//             Log.i("ACTION_MOVE",offsetY+"");
//
//                break;
//            case MotionEvent.ACTION_UP:
//
//                int rawY1 = (int)event.getRawY();
//                Log.i("ACTION_UP",(rawY1-rawy)+"");
//
//                break;
//        }
//
//
//        return true;
//    }
}
