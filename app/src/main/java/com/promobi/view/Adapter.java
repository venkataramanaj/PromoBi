package com.promobi.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.promobi.R;
import com.promobi.databinding.RowItemBinding;
import com.promobi.model.ImageFetch;
import com.promobi.model.Result;

import java.util.List;

/**
 * Created by Rajesh Kumar on 16-05-2018.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;
    List<Result> arrayList;
    RowItemBinding itemBinding;

    public Adapter(Context context, List<Result> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final RowItemBinding rowBinding;
        ImageView img;
        public ViewHolder(RowItemBinding itemView) {
            super(itemView.getRoot());
            this.rowBinding = itemView;
            img = (itemView.getRoot()).findViewById(R.id.img);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
         itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        Log.e("url is ","<><>"+arrayList.get(position).getMultimedia().get(0).getUrl());

        holder.rowBinding.setNewslist(arrayList.get(position));

    ImageFetch.getInstance().loadImage(context,holder.img,arrayList.get(position).getMultimedia().get(0).getUrl());


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
