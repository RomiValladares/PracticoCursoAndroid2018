package com.mycompany.network.ejemplo_marvels.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.network.R;
import com.mycompany.network.ejemplo_marvels.model.WorldWonder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by romina valladaresromina@gmail.com on 10/11/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<WorldWonder> mDataset;
    private WorldWonderListListener listListener;

    public MyAdapter(ArrayList<WorldWonder> myDataset, WorldWonderListListener listListener) {
        mDataset = myDataset;
        this.listListener = listListener;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        Context context = parent.getContext();
        LayoutInflater from = LayoutInflater.from(context);

        View itemView = from.inflate(R.layout.worldwonder_card_item, parent, false);

        MyViewHolder vh = new MyViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        WorldWonder worldWonder = mDataset.get(position);

        holder.mTextView.setText(worldWonder.getNombre());
        holder.txtCountry.setText(worldWonder.getPais());
        ArrayList<String> imagenes = worldWonder.getImagenes();
        if (imagenes != null && imagenes.size() > 0) {
            Picasso.get().load(imagenes.get(0)).into(holder.imgWorldWonder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listListener.onClick(mDataset.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setData(ArrayList<WorldWonder> data) {
        this.mDataset = data;
    }

    public interface WorldWonderListListener {
        void onClick(WorldWonder data);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView txtCountry;
        public ImageView imgWorldWonder;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.txt_list_item);
            imgWorldWonder = v.findViewById(R.id.img_country);
            txtCountry = v.findViewById(R.id.txt_country);
        }
    }


}
