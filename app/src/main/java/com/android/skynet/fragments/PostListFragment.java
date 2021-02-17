package com.android.skynet.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.skynet.R;
import com.android.skynet.models.PostsModel;


public class PostListFragment extends Fragment {
    CardView cardParent;
    TextView txtTitle,txtDescription;

    PostsModel data;

    public PostListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           data= (PostsModel) getArguments().getSerializable("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_list, container, false);;
        cardParent=view.findViewById(R.id.cardParent);
        txtTitle=view.findViewById(R.id.txtTitle);
        txtDescription=view.findViewById(R.id.txtDescription);

        txtTitle.setText(data.getTitle());
        txtDescription.setText(data.getBody());
        return view;
    }
}