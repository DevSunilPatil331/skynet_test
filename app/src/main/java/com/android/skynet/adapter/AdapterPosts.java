package com.android.skynet.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.skynet.R;
import com.android.skynet.models.PostsModel;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.HolderPosts> {
    Context context;
    List<PostsModel> list;
    String TAG="AdapterPosts";
    HolderPosts holderPosts;

    public AdapterPosts(Context context, List<PostsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG,"onCreateViewHolder = "+viewType);
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_posts,parent,false);
        return new HolderPosts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPosts holder, int position) {
        holderPosts=holder;
        Log.e(TAG,"onBindViewHolder = "+position);
        holder.setIsRecyclable(false);
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDescription.setText(list.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    int margin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10, context.getResources().getDisplayMetrics());
//                    ViewGroup.MarginLayoutParams layoutParams =
//                            (ViewGroup.MarginLayoutParams) holder.cardParent.getLayoutParams();
//                    layoutParams.setMargins(0, margin, 0, margin);
//                    holder.cardParent.requestLayout();
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e(TAG,"getItemCount");
        return list.size();
    }

    public void setVisibleView(int visible_item) {
        if (holderPosts!=null){
            try {
                int margin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10, context.getResources().getDisplayMetrics());
                ViewGroup.MarginLayoutParams layoutParams =
                        (ViewGroup.MarginLayoutParams) holderPosts.cardParent.getLayoutParams();
                layoutParams.setMargins(0, margin, 0, margin);
                holderPosts.cardParent.requestLayout();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public class HolderPosts extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription;
        CardView cardParent;

        public HolderPosts(@NonNull View itemView) {
            super(itemView);
            Log.e(TAG,"holder = "+getAdapterPosition());
            cardParent=itemView.findViewById(R.id.cardParent);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDescription=itemView.findViewById(R.id.txtDescription);
        }
    }
}
