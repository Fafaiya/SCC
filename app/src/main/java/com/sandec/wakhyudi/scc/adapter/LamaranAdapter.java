package com.sandec.wakhyudi.scc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.model.Perusahaan;


import java.util.List;

/**
 * Created by wakhyudi on 23/01/18.
 */

public class LamaranAdapter extends RecyclerView.Adapter<LamaranAdapter.LamaranViewHolder> {

    private Context context;
    private List<Perusahaan> listLamaran;

    public LamaranAdapter(Context context, List<Perusahaan> listLamaran) {
        this.context = context;
        this.listLamaran = listLamaran;
    }

    @Override
    public LamaranAdapter.LamaranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_lamaran, parent, false);
        return new LamaranAdapter.LamaranViewHolder(itemView);
    }

    public class LamaranViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLamaran;
        TextView tvLamaran;
        Button btnEditLamaran,btnDeleteLamaran;
        public LamaranViewHolder(View itemView) {
            super(itemView);
            ivLamaran = (ImageView) itemView.findViewById(R.id.iv_item_list_lamaran);
            tvLamaran = (TextView) itemView.findViewById(R.id.tv_item_list_lamaran_nama_perusahaan);
            btnEditLamaran = (Button) itemView.findViewById(R.id.btn_item_lamaran_edit);
            btnDeleteLamaran = (Button) itemView.findViewById(R.id.btn_item_lamaran_delete);
        }
    }


    @Override
    public void onBindViewHolder(LamaranAdapter.LamaranViewHolder holder, final int position) {
        String linkIcon = listLamaran.get(position).getLinkIcon();
        Glide.with(context).load("https://drive.google.com/thumbnail?id="+linkIcon).into(holder.ivLamaran);
        holder.tvLamaran.setText(listLamaran.get(position).getNamaPerusahaan());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                
//                
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listLamaran.size();
    }


}
