package com.android.skynet.api;

import com.android.skynet.models.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("posts")
    Call<List<PostsModel>> getPosts();
}
