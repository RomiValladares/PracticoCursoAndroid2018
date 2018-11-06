package com.mycompany.network.ejemplo_countries.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_countries.model.Country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by romina valladaresromina@gmail.com on 10/11/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Country> mDataset;

    public MyAdapter(ArrayList<Country> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getName());
        Picasso.get().load(mDataset.get(position).getFlag()).into(holder.imgCountry);
    }

    @Override
    public int getItemCount() {
        Log.d("asdas", "item count:" + mDataset.size());
        return mDataset.size();
    }

    public void setData(ArrayList<Country> data) {
        this.mDataset = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView imgCountry;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.txt_list_item);
            imgCountry = v.findViewById(R.id.img_country);
        }
    }
}
