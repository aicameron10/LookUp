package com.bol.lookup.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bol.lookup.R;
import com.bol.lookup.activities.MainActivity;
import com.bol.lookup.activities.ReviewActivity;
import com.bol.lookup.adapters.ReviewsAdapter;
import com.bol.lookup.model.Count;
import com.bol.lookup.model.NavItem;
import com.bol.lookup.model.Review;
import com.bol.lookup.model.ReviewsResponse;
import com.bol.lookup.rest.ApiClientReviews;
import com.bol.lookup.rest.ApiInterfaceReviews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragmentTab extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Count> counts;
    private ReviewsAdapter adapter;

    View rootView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    RecyclerView recyclerView;

    TextView total;
    TextView star1;
    TextView star2;
    TextView star3;
    TextView star4;
    TextView star5;

    ProgressBar bar1;
    ProgressBar bar2;
    ProgressBar bar3;
    ProgressBar bar4;
    ProgressBar bar5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_reviews_tab, container, false);

        pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

        ((MainActivity) getActivity()).loadDrawsHide();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/webfont.ttf");

        TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        title.setTypeface(face, Typeface.BOLD);
        title.setTextSize(18);
        title.setText("Detail");

        NavItem nav = new NavItem();
        nav.setPage("Home");

        total = (TextView) rootView.findViewById(R.id.total);
        star1 = (TextView) rootView.findViewById(R.id.total_1);
        star2 = (TextView) rootView.findViewById(R.id.total_2);
        star3 = (TextView) rootView.findViewById(R.id.total_3);
        star4 = (TextView) rootView.findViewById(R.id.total_4);
        star5 = (TextView) rootView.findViewById(R.id.total_5);

        bar1 = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        bar2 = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        bar3 = (ProgressBar) rootView.findViewById(R.id.progressBar3);
        bar4 = (ProgressBar) rootView.findViewById(R.id.progressBar4);
        bar5 = (ProgressBar) rootView.findViewById(R.id.progressBar5);

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.reviews_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        fetchReviews();

        return rootView;
    }

    private void fetchReviews() {

        ApiInterfaceReviews apiService =
                ApiClientReviews.getClient().create(ApiInterfaceReviews.class);

        String productID = pref.getString("productId", "");

        Call<ReviewsResponse> call = apiService.getReviews(productID);


        call.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                int statusCode = response.code();
                List<Review> results = response.body().getReviews();
                adapter = new ReviewsAdapter(results, R.layout.list_item_review, getActivity());
                if (!results.isEmpty()) {
                    counts = response.body().getCounts();
                    if (!counts.isEmpty()) {
                        total.setText(Integer.toString(counts.get(0).getTotal()));
                        star1.setText(Integer.toString(counts.get(0).getOnestar()));
                        star2.setText(Integer.toString(counts.get(0).getTwostar()));
                        star3.setText(Integer.toString(counts.get(0).getThreestar()));
                        star4.setText(Integer.toString(counts.get(0).getFourstar()));
                        star5.setText(Integer.toString(counts.get(0).getFivestar()));
                        bar1.setProgress(counts.get(0).getOnestar());
                        bar2.setProgress(counts.get(0).getTwostar());
                        bar3.setProgress(counts.get(0).getThreestar());
                        bar4.setProgress(counts.get(0).getFourstar());
                        bar5.setProgress(counts.get(0).getFivestar());
                    } else {

                    }


                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            fetchReviews();
        }

    }

}
