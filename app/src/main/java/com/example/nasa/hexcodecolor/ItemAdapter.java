package com.example.nasa.hexcodecolor;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nasa on 20-Feb-18.
 */

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    public int layout_id;
    public ArrayList<HexAdapter> itemlist;

    public ItemAdapter(int layout_id, ArrayList<HexAdapter> itemlist) {
        this.layout_id = layout_id;
        this.itemlist = itemlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout_id,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView textView = holder.textView;
        ImageView img = holder.imageView;

        textView.setText(itemlist.get(position).getColor());
        img.setBackgroundColor(Color.parseColor(itemlist.get(position).getColor()));

    }

    @Override
    public int getItemCount() {
        return itemlist != null?itemlist.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.colorHex);


        }
    }
}
