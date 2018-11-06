package com.mycompany.network.ejemplo_marvels.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mycompany.network.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by romina valladaresromina@gmail.com on 10/17/18.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {

    private final ArrayList<String> mDataset;

    public ImageListAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ImageListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_cardimage, parent, false);

        ImageListAdapter.MyViewHolder vh = new ImageListAdapter.MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.MyViewHolder holder, int position) {
        String imgUrl = mDataset.get(position);
        Picasso.get().load(imgUrl).into(holder.imgWorldWonder);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgWorldWonder;

        public MyViewHolder(View v) {
            super(v);
            imgWorldWonder = v.findViewById(R.id.img_country);
        }
    }
}
