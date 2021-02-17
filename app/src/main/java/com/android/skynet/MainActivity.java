package com.android.skynet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.Toast;

import com.android.skynet.adapter.AdapterPosts;
import com.android.skynet.models.PostsModel;
import com.android.skynet.viewmodels.PostViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView_Post;
    AdapterPosts adapterPosts;
    PostViewModel postViewModel;

    List<PostsModel> posts_list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel= ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(this, new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
                posts_list.clear();
                posts_list.addAll(postsModels);
                adapterPosts.notifyDataSetChanged();
                if (postsModels.size()==0){
                    Snackbar.make(recyclerView_Post,"No Data Found",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        initViews();
        recyclerView_Post.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCREEN_STATE_OFF){
                    LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visible_item = linearLayoutManager.findFirstVisibleItemPosition();
                    Toast.makeText(MainActivity.this, "visible pos= "+visible_item, Toast.LENGTH_SHORT).show();
                    adapterPosts.setVisibleView(visible_item);
                }
//                Toast.makeText(MainActivity.this, "Scroll state = "+newState, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerView_Post.setClipToPadding(false);
        recyclerView_Post.setClipChildren(false);
//        recyclerView_Post.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-(Math.abs(position));
                page.setScaleY(0.85f+ r *0.15f);
            }
        });



    }

    private void initViews() {
        recyclerView_Post=findViewById(R.id.recyclerView_Post);
        recyclerView_Post.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        adapterPosts=new AdapterPosts(this,posts_list);
        recyclerView_Post.setAdapter(adapterPosts);
    }
}