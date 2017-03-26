package com.bol.lookup.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bol.lookup.R;
import com.bol.lookup.activities.MainActivity;
import com.bol.lookup.model.NavItem;


public class DetailFragmentTab extends Fragment  {


    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);



        rootView = inflater.inflate(R.layout.fragment_detail_tab, container, false);


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


        return rootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

}
