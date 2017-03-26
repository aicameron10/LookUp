package com.bol.lookup.fragments;

/**
 * Created by Andrew Cameron
 */

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bol.lookup.R;
import com.bol.lookup.activities.MainActivity;
import com.bol.lookup.adapters.SearchAdapter;
import com.bol.lookup.model.NavItem;
import com.bol.lookup.model.SearchProducts;
import com.bol.lookup.model.SearchResponse;
import com.bol.lookup.rest.ApiClient;
import com.bol.lookup.rest.ApiInterface;
import com.bol.lookup.utils.ConnectionDetector;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bol.lookup.Constants.API_KEY;


public class HomeFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<SearchProducts> searchList;
    View rootView;

    Boolean isInternetPresent = false;
    ConnectionDetector cd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        checkInternet();

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).loadDrawsHide();
        ((MainActivity) getActivity()).loadDrawsDisplay();
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/webfont.ttf");

        TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        title.setTypeface(face, Typeface.BOLD);
        title.setTextSize(18);
        title.setText("LookUp");

        NavItem nav = new NavItem();
        nav.setPage("HomePage");

        initCollapsingToolbar();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        searchList = new ArrayList<>();
        adapter = new SearchAdapter(getActivity(), searchList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
            Glide.with(this).load(R.drawable.deals).into((ImageView) rootView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> data = new HashMap<>();
        data.put("apikey", API_KEY);
        data.put("q", "halo");
        data.put("format", "json");

        Call<SearchResponse> call = apiService.getSearchResults(data);


        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                int statusCode = response.code();
                List<SearchProducts> results = response.body().getProducts();
                recyclerView.setAdapter(new SearchAdapter(getActivity(), results));
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        return rootView;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onResume() {
        super.onResume();
        checkInternet();
    }

    private void checkInternet() {

        try {
            cd = new ConnectionDetector(getActivity());

            // Check if Internet present
            isInternetPresent = cd.isConnectingToInternet();

            if (!isInternetPresent) {
                RelativeLayout RelativeLayout = (android.widget.RelativeLayout) rootView.findViewById(R.id
                        .main_content);
                Snackbar snackbar = Snackbar
                        .make(RelativeLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                        .setDuration(10000)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                ((MainActivity) getActivity()).displayView(0);
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
