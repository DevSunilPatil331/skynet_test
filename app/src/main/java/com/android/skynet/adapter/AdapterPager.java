package com.android.skynet.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.skynet.fragments.PostListFragment;
import com.android.skynet.models.PostsModel;

import java.util.List;

public class AdapterPager extends FragmentStateAdapter {
    Context context;
    List<PostsModel> list;
    String TAG="AdapterPosts";
    AdapterPosts.HolderPosts holderPosts;
    public AdapterPager(@NonNull Context context,List<PostsModel> list) {
        super((FragmentActivity) context);
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",list.get(position));

        PostListFragment postListFragment=new PostListFragment();
        postListFragment.setArguments(bundle);
        return postListFragment;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
