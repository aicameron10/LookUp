package com.bol.lookup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bol.lookup.R;
import com.bol.lookup.model.DetailResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.MyViewHolder> {

    private Context mContext;

    private ArrayList<DetailResponse> selectionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }


    public RelatedAdapter(Context mContext, ArrayList<DetailResponse> selectionList) {
        this.mContext = mContext;
        this.selectionList = selectionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DetailResponse selection = selectionList.get(position);
        holder.title.setText(selection.getProducts().get(0).getTitle());


        // loading using Glide library
        Glide.with(mContext).load(selection.getProducts().get(0).getMedia().get(0).getUrl()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }


    @Override
    public int getItemCount() {
        return selectionList.size();
    }
}