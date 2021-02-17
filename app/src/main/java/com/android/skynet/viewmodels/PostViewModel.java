package com.android.skynet.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.skynet.api.NetworkController;
import com.android.skynet.models.PostsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends AndroidViewModel {
    MutableLiveData<List<PostsModel>> data;
    NetworkController networkController;

    public PostViewModel(@NonNull Application application) {
        super(application);
        data=new MutableLiveData<>();
        networkController=NetworkController.getInstance();
    }
    public LiveData<List<PostsModel>> getPosts(){
        networkController.getPosts(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                data.postValue(new ArrayList<>());
            }
        });
        return data;
    }

}
