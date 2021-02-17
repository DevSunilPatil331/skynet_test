package com.android.skynet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.android.skynet.adapter.AdapterPager;
import com.android.skynet.adapter.AdapterPosts;
import com.android.skynet.models.PostsModel;
import com.android.skynet.viewmodels.PostViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ViewPager2 pager;
    PostViewModel postViewModel;

    List<PostsModel> posts_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pager=findViewById(R.id.pager);

        postViewModel= ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(this, new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
                posts_list.clear();
                posts_list.addAll(postsModels);
//                pager.setAdapter(new AdapterPager(HomeActivity.this,posts_list));
                pager.setAdapter(new AdapterPosts(HomeActivity.this,posts_list));
                if (postsModels.size()==0){
                    Snackbar.make(pager,"No Data Found",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        pager.setClipToPadding(false);
        pager.setClipChildren(false);
        pager.setOffscreenPageLimit(3);
        pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        pager.setPageTransformer(compositePageTransformer);

    }
}