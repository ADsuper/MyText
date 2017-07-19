package io.github.adsuper.mytext.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.adsuper.mytext.R;
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
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView mImageview;

    int drawableResouce = R.drawable.guide_bg_me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        RetrofitManager.getInstance().getDataFromServer_rxjava();
//        RetrofitManager.getInstance().getDataFromServer_retrofit();
//        RxjavaGuanChaZheMoShi.dingyue();
//        setImageview();
//        RetrofitManager.getInstance().getDataFromServer_rxjava_flatmap();
        setImageview_map();
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
}
