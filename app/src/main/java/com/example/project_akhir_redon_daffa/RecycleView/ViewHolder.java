package com.example.project_akhir_redon_daffa.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_akhir_redon_daffa.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView _name, _date, _details;
    ImageButton _btn_delete, _btn_edit;
    ImageButton _btn_web;
    ImageView _foto;

    Context _context;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        _context = itemView.getContext();
        _name = itemView.findViewById(R.id.txNama_beasiswa);
        _date = itemView.findViewById(R.id.txTanggal_beasiswa);
        _details = itemView.findViewById(R.id.txDetails);
        _btn_web = itemView.findViewById(R.id.btn_web);
        _btn_delete = itemView.findViewById(R.id.btn_delete);
        _btn_edit = itemView.findViewById(R.id.btn_edit);
        _foto = itemView.findViewById(R.id.foto_id);
    }
}
