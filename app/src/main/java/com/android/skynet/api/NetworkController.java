package com.android.skynet.api;

import com.android.skynet.models.PostsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {
    private static final String BASE_URL="https://jsonplaceholder.typicode.com/";
    private  static Retrofit retrofit;
    private ApiInterface apiInterface;

    private static NetworkController networkController=new NetworkController();

    private NetworkController() {
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface=retrofit.create(ApiInterface.class);
        }
    }

    public static NetworkController getInstance() {
        return networkController;
    }

    public void getPosts(Callback<List<PostsModel>> callback){
        Call<List<PostsModel>> call=apiInterface.getPosts();
        call.enqueue(callback);
    }
}
