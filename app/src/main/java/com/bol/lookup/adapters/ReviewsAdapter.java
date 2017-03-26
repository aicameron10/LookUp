package com.bol.lookup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.bol.lookup.R;
import com.bol.lookup.model.Review;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> reviews;
    private int rowLayout;
    private Context context;


    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reviewsLayout;
        TextView reviewTitle;
        TextView date;
        TextView reviewDescription;
        TextView rating;
        TextView email;


        public ReviewViewHolder(View v) {
            super(v);
            reviewsLayout = (LinearLayout) v.findViewById(R.id.reviews_layout);
            reviewTitle = (TextView) v.findViewById(R.id.review_title);
            date = (TextView) v.findViewById(R.id.review_date);
            reviewDescription = (TextView) v.findViewById(R.id.review_detail);
            rating = (TextView) v.findViewById(R.id.review_rating);
            email = (TextView) v.findViewById(R.id.review_email);
        }
    }

    public ReviewsAdapter(List<Review> reviews, int rowLayout, Context context) {
        this.reviews = reviews;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ReviewViewHolder holder, final int position) {
        holder.reviewTitle.setText(reviews.get(position).getTitle());
        holder.date.setText(reviews.get(position).getCreated());
        holder.reviewDescription.setText(reviews.get(position).getReview());
        holder.email.setText(reviews.get(position).getEmail());
        holder.rating.setText(Integer.toString(reviews.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}