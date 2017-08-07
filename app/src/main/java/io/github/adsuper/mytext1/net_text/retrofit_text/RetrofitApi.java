package io.github.adsuper.mytext1.net_text.retrofit_text;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：luoshen/rsf411613593@gmail.com
 * 时间：2017年07月19日
 * 说明：retrofit 的网络请求接口
 */

public interface RetrofitApi {

    /**
     *
     * @param key 网络请求的 key
     * @param num 每次请求数据的大小
     * @param page 第几页数据
     * @return
     */
    @GET("mobile/")
    Observable<ReadModel> getReadData_Observable(@Query("key") String key,
                                      @Query("num") int num,
                                      @Query("page") int page);

    /**
     *
     * @param key 网络请求的 key
     * @param num 每次请求数据的大小
     * @param page 第几页数据
     * @return
     */
    @GET("mobile/")
    Call<ReadModel> getReadData_Call(@Query("key") String key,
                                     @Query("num") int num,
                                     @Query("page") int page);
}
