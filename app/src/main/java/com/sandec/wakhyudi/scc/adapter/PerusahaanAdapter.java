package com.sandec.wakhyudi.scc.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.activities.DetailLowonganActivity;
import com.sandec.wakhyudi.scc.model.Perusahaan;

import java.util.List;

/**
 * Created by wakhyudi on 22/01/18.
 */

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanAdapter.PerusahaanViewHolder> {
    private Context context;
    private List<Perusahaan> listPerusahaan;

    public PerusahaanAdapter(Context context, List<Perusahaan> listPerusahaan) {
        this.context = context;
        this.listPerusahaan = listPerusahaan;
    }

    @Override
    public PerusahaanAdapter.PerusahaanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_lowongan, parent, false);
        return new PerusahaanViewHolder(itemView);
    }

    public class PerusahaanViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPerusahaan;
        TextView tvPerusahaan;

        public PerusahaanViewHolder(View itemView) {
            super(itemView);
            ivPerusahaan = (ImageView) itemView.findViewById(R.id.iv_item_home);
            tvPerusahaan = (TextView) itemView.findViewById(R.id.tv_item_home);
        }
    }


    @Override
    public void onBindViewHolder(PerusahaanAdapter.PerusahaanViewHolder holder, final int position) {
        final String linkIcon = listPerusahaan.get(position).getLinkIcon();
        Glide.with(context).load("https://drive.google.com/thumbnail?id="+linkIcon).into(holder.ivPerusahaan);
        holder.tvPerusahaan.setText(listPerusahaan.get(position).getNamaPerusahaan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailLowonganActivity.class);
                Bundle bundle = new Bundle();
                String namaPerusahaan = listPerusahaan.get(position).getNamaPerusahaan();
                String descLamaran = listPerusahaan.get(position).getDescription();
                bundle.putString("perusahaan",namaPerusahaan);
                bundle.putString("iconPerusahaan",linkIcon);
                bundle.putString("deskripsiPerusahaan",descLamaran);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listPerusahaan.size();
    }


}
