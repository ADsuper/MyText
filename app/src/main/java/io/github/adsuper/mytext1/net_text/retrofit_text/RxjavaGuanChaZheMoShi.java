package io.github.adsuper.mytext1.net_text.retrofit_text;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：luoshen/rsf411613593@gmail.com
 * 时间：2017年07月19日
 * 说明：RxJava 观察者模式示例
 */

public class RxjavaGuanChaZheMoShi {
    /**
     * 观察者--创建方式一
     */
    static Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {
            Log.i("RxjavaGuanChaZheMoShi","value:-----"+value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            Log.i("RxjavaGuanChaZheMoShi","onComplete");
        }
    };
    /**
     * 观察者--创建方式二
     */
    Subscriber<String> mSubscriber = new Subscriber<String>() {
        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };
//---------------------------------------------------------------------------------------------------------------------------

    /**
     * 被观察者--创建方式一
     */
    static Observable mObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            //在订阅之后 ，通过调用 onNext 方法传递数据
            e.onNext("张三1");
            e.onNext("张三2");
            e.onNext("张三3");
            e.onNext("张三4");
            e.onComplete();
        }
    });
    /**
     * 被观察者--创建方式二
     */
    Observable mObservable2 = Observable.just("Hello", "Hi", "Aloha");
    // 将会依次调用：
    // onNext("Hello");
    // onNext("Hi");
    // onNext("Aloha");
    // onCompleted();

    /**
     * 被观察者--创建方式三
     */
    String[] words = {"Hello", "Hi", "Aloha"};
    Observable mObservable3 = Observable.fromArray(words);
    // 将会依次调用：
    // onNext("Hello");
    // onNext("Hi");
    // onNext("Aloha");
    // onCompleted();

//-----------------------------------------------------------------------------------------------------
    /**
     * 将被观察者和观察者联系起来 通过：subscribe 方法
     */
    public static void dingyue(){

        mObservable.subscribe(mObserver);

    }

}
