package io.github.adsuper.mytext1.net_text.retrofit_text;

import android.util.Log;

import java.util.List;

import io.github.adsuper.mytext1.common11.Constant;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：luoshen/rsf411613593@gmail.com
 * 时间：2017年07月19日
 * 说明：Retrofit 网络请求封装类
 */

public class RetrofitManager {
    public static RetrofitManager retrofitManager;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    /**
     * retrofit 配合 rxjava
     */
    public void getDataFromServer_rxjava() {

        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_READ)
                //配合 rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用 Gson 解析
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class)
                .getReadData_Observable(Constant.APIKEY, Constant.PAGE_SIZE, 2)
                //指定网络请求在 io 线程
                .subscribeOn(Schedulers.io())
                //指定数据处理在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("RetrofitManager", "onSubscribe");
                    }

                    @Override
                    public void onNext(ReadModel value) {
                        Log.i("RetrofitManager", "onNext");
                        int code = value.getCode();
                        String msg = value.getMsg();
                        Log.i("RetrofitManager", "code----" + code + "\n"
                                + " msg----" + msg
                        );
                        List<ReadModel.NewslistEntity> newslist = value.getNewslist();
                        for (int i = 0; i < newslist.size(); i++) {
                            ReadModel.NewslistEntity newslistEntity = newslist.get(i);
                            String title = newslistEntity.getTitle();
                            String ctime = newslistEntity.getCtime();
                            String picUrl = newslistEntity.getPicUrl();
                            String url = newslistEntity.getUrl();
                            String description = newslistEntity.getDescription();
                            Log.i("RetrofitManager", "title----" + title + "\n"
                                    + " ctime----" + ctime + "\n"
                                    + " description----" + description + "\n"
                                    + " picUrl----" + picUrl + "\n"
                                    + " url----" + url + "\n"
                            );
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RetrofitManager", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("RetrofitManager", "onComplete");
                    }
                });
    }

    /**
     * 单纯使用  retrofit
     */
    public void getDataFromServer_retrofit() {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL_READ)
                .build()
                .create(RetrofitApi.class)
                .getReadData_Call(Constant.APIKEY, Constant.PAGE_SIZE, 2)
                .enqueue(new Callback<ReadModel>() {
                    @Override
                    public void onResponse(Call<ReadModel> call, Response<ReadModel> response) {
                        int code = response.code();
                        String message = response.message();
                        Log.i("RetrofitManager", "code----" + code + "\n"
                                + " message----" + message
                        );
                        ReadModel body = response.body();
                        int code1 = body.getCode();
                        String msg = body.getMsg();
                        Log.i("RetrofitManager", "code1----" + code1 + "\n"
                                + " msg----" + msg
                        );
                        List<ReadModel.NewslistEntity> newslist = body.getNewslist();

                        for (int i = 0; i < newslist.size(); i++) {
                            ReadModel.NewslistEntity newslistEntity = newslist.get(i);
                            String title = newslistEntity.getTitle();
                            String ctime = newslistEntity.getCtime();
                            String picUrl = newslistEntity.getPicUrl();
                            String url = newslistEntity.getUrl();
                            String description = newslistEntity.getDescription();
                            Log.i("RetrofitManager", "title----" + title + "\n"
                                    + " ctime----" + ctime + "\n"
                                    + " description----" + description + "\n"
                                    + " picUrl----" + picUrl + "\n"
                                    + " url----" + url + "\n"
                            );
                        }

                    }

                    @Override
                    public void onFailure(Call<ReadModel> call, Throwable t) {

                    }
                });
    }

    /**
     * retrofit 配合 rxjava 使用 flatmap 变换
     */
    public void getDataFromServer_rxjava_flatmap() {

        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_READ)
                //配合 rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用 Gson 解析
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class)
                .getReadData_Observable(Constant.APIKEY, Constant.PAGE_SIZE, 2)
                .flatMap(new Function<ReadModel, Observable<ReadModel.NewslistEntity>>() {
                    @Override
                    public Observable<ReadModel.NewslistEntity> apply(final ReadModel readModel) throws Exception {
                        return Observable.fromIterable(readModel.getNewslist());
                    }
                })
                //指定网络请求在 io 线程
                .subscribeOn(Schedulers.io())
                //指定数据处理在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadModel.NewslistEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReadModel.NewslistEntity value) {
                        String ctime = value.getCtime();
                        Log.i("RetrofitManager","ctime-----"+ctime);
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
     * retrofit 配合 rxjava 使用 doOnNext 对数据进行二次耗时处理，比如说：与数据库比对、写入本地
     */
    public void getDataFromServer_rxjava_doOnNext() {

        new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_READ)
                //配合 rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用 Gson 解析
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class)
                .getReadData_Observable(Constant.APIKEY, Constant.PAGE_SIZE, 2)
                //对返回的数据进行二次耗时处理
                .doOnNext(new Consumer<ReadModel>() {
                    @Override
                    public void accept(ReadModel readModel) throws Exception {

                    }
                })
                .flatMap(new Function<ReadModel, Observable<ReadModel.NewslistEntity>>() {
                    @Override
                    public Observable<ReadModel.NewslistEntity> apply(final ReadModel readModel) throws Exception {
                        return Observable.fromIterable(readModel.getNewslist());
                    }
                })
                //指定网络请求在 io 线程
                .subscribeOn(Schedulers.io())
                //指定数据处理在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadModel.NewslistEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReadModel.NewslistEntity value) {
                        String ctime = value.getCtime();
                        Log.i("RetrofitManager","ctime-----"+ctime);
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
