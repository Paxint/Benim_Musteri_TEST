package com.eraybarisbahadir.benim_musteri_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eraybarisbahadir.benim_musteri_test.model.Talep;

import java.util.List;

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<Talep> itemList1;
    private Context context;

    public ItemAdapter(List<Talep> itemList,Context context) {

        this.itemList1=itemList;
        this.context=context;

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, final int position) {

        holder.itemImage.getDrawable();
        holder.itemtxt.setText(itemList1.get(position).getTicket_id());


    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemtxt;
        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.itemImg);
            itemtxt=itemView.findViewById(R.id.itemName);
            linearLayout=itemView.findViewById(R.id.layot_id);

        }
    }
}