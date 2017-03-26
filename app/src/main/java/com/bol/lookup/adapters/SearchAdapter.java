package com.bol.lookup.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bol.lookup.R;
import com.bol.lookup.activities.MainActivity;
import com.bol.lookup.model.SearchProducts;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Andrew Cameron
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private Context mContext;
    private List<SearchProducts> searchList;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public SearchAdapter(Context mContext, List<SearchProducts> searchList) {
        this.mContext = mContext;
        this.searchList = searchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SearchProducts results = searchList.get(position);
        holder.title.setText(results.getTitle());
        holder.count.setText("$" + String.valueOf(results.getOfferData().getOffers().get(0).getPrice()));
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref = mContext.getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
                editor = pref.edit();
                editor.putString("productId", results.getId());
                editor.apply();

                ((MainActivity) mContext).displayView(1);

            }
        });


        // loading album cover using Glide library
        Glide.with(mContext).load(results.getImages().get(3).getUrl()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_wish_list:
                    Toast.makeText(mContext, "Add to wish list", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_add_cart:
                    Toast.makeText(mContext, "Add to cart", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
