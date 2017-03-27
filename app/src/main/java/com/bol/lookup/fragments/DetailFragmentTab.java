package com.bol.lookup.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bol.lookup.R;
import com.bol.lookup.activities.MainActivity;
import com.bol.lookup.adapters.GalleryAdapter;
import com.bol.lookup.adapters.RecommendationsAdapter;
import com.bol.lookup.adapters.RelatedAdapter;
import com.bol.lookup.model.Accessories;
import com.bol.lookup.model.DetailProducts;
import com.bol.lookup.model.DetailResponse;
import com.bol.lookup.model.Media;
import com.bol.lookup.model.NavItem;
import com.bol.lookup.model.RelatedResponse;
import com.bol.lookup.rest.ApiClient;
import com.bol.lookup.rest.ApiInterface;
import com.bol.lookup.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bol.lookup.Constants.API_KEY;

public class DetailFragmentTab extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Media> images;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewRecommendations;
    private RecyclerView recyclerViewRelated;
    private RecommendationsAdapter adapter;
    private RelatedAdapter adapterRelated;

    ArrayList<String> listOfProductId = new ArrayList<>();

    private final ArrayList<Subscription> subscriptions = new ArrayList<>();
    private final ArrayList<DetailResponse> products = new ArrayList<>();


    View rootView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ProgressBar pg;
    LinearLayout lin,lin1;

    TextView titleTxt, summary, price, oldPrice, shortDesc, avail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        rootView = inflater.inflate(R.layout.fragment_detail_tab, container, false);

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

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        titleTxt = (TextView) rootView.findViewById(R.id.title);
        summary = (TextView) rootView.findViewById(R.id.summary);
        price = (TextView) rootView.findViewById(R.id.price);
        oldPrice = (TextView) rootView.findViewById(R.id.oldPrice);
        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        shortDesc = (TextView) rootView.findViewById(R.id.shortDesc);
        avail = (TextView) rootView.findViewById(R.id.avail);

        lin = (LinearLayout) rootView.findViewById(R.id.lin3);
        lin1 = (LinearLayout) rootView.findViewById(R.id.lin2);

        lin.setVisibility(View.GONE);
        lin1.setVisibility(View.GONE);

        NestedScrollView nsv = (NestedScrollView) rootView.findViewById(R.id.scrollView1);
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });

        pg = (ProgressBar) rootView.findViewById(R.id.progressBar);

        recyclerViewRecommendations = (RecyclerView) rootView.findViewById(R.id.recycler_view1);
        recyclerViewRelated = (RecyclerView) rootView.findViewById(R.id.recycler_view2);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewRecommendations.setLayoutManager(layoutManager);
        recyclerViewRecommendations.setItemAnimator(new DefaultItemAnimator());

        recyclerViewRelated.setLayoutManager(layoutManager1);
        recyclerViewRelated.setItemAnimator(new DefaultItemAnimator());

        adapterRelated = new RelatedAdapter(getActivity(), products);
        recyclerViewRelated.setAdapter(adapterRelated);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));


        fetchDetail();
        fetchRecommendations();
        fetchRelated();

        return rootView;
    }


    private void fetchDetail() {

        pg.setVisibility(View.VISIBLE);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String productID = pref.getString("productId", "");

        Call<DetailResponse> call = apiService.getProducts(productID, API_KEY, "json");


        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                int statusCode = response.code();
                pg.setVisibility(View.GONE);
                if (response.body() != null) {
                    List<DetailProducts> results = response.body().getProducts();
                    if (!results.isEmpty()) {
                        images = results.get(0).getMedia();
                        mAdapter = new GalleryAdapter(getActivity(), images);
                        recyclerView.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    private void fetchRecommendations() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String productID = pref.getString("productId", "");

        Call<DetailResponse> call = apiService.getRecommendations(productID, API_KEY, "json");

        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                int statusCode = response.code();
                if (response.body() != null) {
                    List<DetailProducts> results = response.body().getProducts();
                    if (results != null) {

                        lin1.setVisibility(View.VISIBLE);
                        adapter = new RecommendationsAdapter(getActivity(), results);
                        recyclerViewRecommendations.setAdapter(adapter);

                        titleTxt.setText(results.get(0).getTitle());
                        editor.putString("productName", results.get(0).getTitle());
                        editor.apply();
                        summary.setText(results.get(0).getSummary());
                        shortDesc.setText(Html.fromHtml(results.get(0).getShortDescription()));
                        price.setText("$" + String.valueOf(results.get(0).getOfferData().getOffers().get(0).getPrice()));
                        oldPrice.setText("$" + String.valueOf(results.get(0).getOfferData().getOffers().get(0).getListPrice()));
                        avail.setText(Html.fromHtml(results.get(0).getOfferData().getOffers().get(0).getAvailabilityDescription()));
                    } else {
                        lin1.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void fetchRelated() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String productID = pref.getString("productId", "");

        Call<RelatedResponse> call = apiService.getRelatedProducts(productID, API_KEY, "json");


        call.enqueue(new Callback<RelatedResponse>() {
            @Override
            public void onResponse(Call<RelatedResponse> call, Response<RelatedResponse> response) {
                int statusCode = response.code();
                if (response.body() != null) {
                    List<Accessories> results = response.body().getAccessories();
                    if (results != null) {
                        for (int i = 0; i < results.size(); i++) {
                            lin.setVisibility(View.VISIBLE);
                            Accessories acc = (Accessories) results.get(i);
                            listOfProductId.add(acc.getProductId());
                        }

                        fetchAllProducts();
                    }else{
                        lin.setVisibility(View.GONE);
                    }
                }else{
                    lin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<RelatedResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void fetchAllProducts() {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        for (String i : listOfProductId) {
            Observable<DetailResponse> call = apiService.getProducts(i, API_KEY, "json");

            Subscription subscription = call
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DetailResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                HttpException response = (HttpException) e;
                                int code = response.code();
                                Log.d("RetrofitTest", "Error code: " + code);
                            }
                        }

                        @Override
                        public void onNext(DetailResponse product) {
                            products.add(product);

                            adapterRelated.notifyDataSetChanged();
                        }
                    });


            subscriptions.add(subscription);

            System.out.println(subscriptions.get(0).toString());
        }

    }


    @Override
    public void onDestroy() {
        for (Subscription subscription : subscriptions) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

}
