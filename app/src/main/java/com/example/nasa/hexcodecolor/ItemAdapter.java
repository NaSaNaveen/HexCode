package com.example.nasa.hexcodecolor;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    public int layout_id;
    public static MyClickListener myClickListener;
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

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.colorHex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(itemView.getContext(), getAdapterPosition()+"   "+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    myClickListener.onItemClick(getAdapterPosition(), view);
                }
            });


        }
    }
    public interface MyClickListener{
        void onItemClick(int position,View v);
    }
    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }
}
