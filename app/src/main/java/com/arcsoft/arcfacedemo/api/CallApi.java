package com.arcsoft.arcfacedemo.api;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 *
 * @author mementto
 * @date 2020/4/16
 * retrofit网络请求接口
 */
public interface CallApi {

    /**
     * 上传图像
     * @param img 图像文件
     * @return 请求体
     */
    @POST("user/image/upload")
    @Multipart
    Call<Integer> uploadImage(@Part MultipartBody.Part img, @Query("userId") Long userId);

    @POST("image/recognize")
    @Multipart
    Call<ResponseBody> recognizeImage(@Part MultipartBody.Part img);

    @POST("user/login")
    Call<Long> login(@Query("username") String username, @Query("password") String password);

    @POST("user/register/username")
    Call<Integer> submitUsername(@Query("username") String username);

    @POST("user/register")
    Call<Long> register(@Query("username") String username, @Query("password") String password, @Query("passwordAgain") String passwordAgain);
}
