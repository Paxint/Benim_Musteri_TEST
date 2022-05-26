package com.eraybarisbahadir.benim_musteri_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eraybarisbahadir.benim_musteri_test.databinding.RecyclerRowBinding;
import com.eraybarisbahadir.benim_musteri_test.model.Talep;

import java.util.ArrayList;

public class TalepAdapter extends RecyclerView.Adapter<TalepAdapter.TalepHolder> {

    private ArrayList<Talep> talepArrayList;

    public TalepAdapter(ArrayList<Talep> talepArrayList) {
            this.talepArrayList = talepArrayList;
    }

    @NonNull
    @Override
    public TalepAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TalepHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull TalepAdapter.TalepHolder holder, int position) {
        holder.recyclerRowBinding.recycler_view_ticket.setText(talepArrayList).get(position).ID);
    }


    @Override
    public int getItemCount() {
        return talepArrayList.size();
    }

    class TalepHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding recyclerRowBinding;

        public TalepHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
