package com.example.project_akhir_redon_daffa.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_akhir_redon_daffa.Main.AddData;
import com.example.project_akhir_redon_daffa.Main.EditData;
import com.example.project_akhir_redon_daffa.Main.Login;
import com.example.project_akhir_redon_daffa.Main.MainActivity;
import com.example.project_akhir_redon_daffa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Beasiswa> ListBea;
    private Context context;
    FirebaseDataListener listener;
    public Adapter(List<Beasiswa> ListBea, Context context) {
        this.ListBea = ListBea;
        this.context = context;
        listener = (MainActivity)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder._name.setText(ListBea.get(position).getNama());
        holder._date.setText(ListBea.get(position).getTanggal());
        holder._details.setText(ListBea.get(position).getDetails());
        Glide.with(context).load(ListBea.get(position).getFoto()).into(holder._foto);
        holder._btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ListBea.get(position).getWeb();
                Uri webpage = Uri.parse(url);
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    webpage = Uri.parse("http://" + url);
                }
                Intent web = new Intent(Intent.ACTION_VIEW, webpage);
                context.startActivity(web);
            }
        });
        holder._btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteData(ListBea.get(position), position);
            }
        });
        holder._btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nama", ListBea.get(position).getNama());
                bundle.putString("tanggal", ListBea.get(position).getTanggal());
                bundle.putString("details", ListBea.get(position).getDetails());
                bundle.putString("web", ListBea.get(position).getWeb());
                bundle.putString("foto", ListBea.get(position).getFoto());
                bundle.putString("key",ListBea.get(position).getKey());
                Intent intent = new Intent(v.getContext(), EditData.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListBea.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Beasiswa beasiswa, int position);
    }
}
