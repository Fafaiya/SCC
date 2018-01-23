package com.sandec.wakhyudi.scc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.activities.ListLamaranActivity;
import com.sandec.wakhyudi.scc.model.Profile;
import com.sandec.wakhyudi.scc.model.Profile;

import java.util.List;

/**
 * Created by wakhyudi on 22/01/18.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private Context context;
    private List<Profile> listProfile;

    public ProfileAdapter(Context context, List<Profile> listProfile) {
        this.context = context;
        this.listProfile = listProfile;
    }

    @Override
    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_lowongan, parent, false);
        return new ProfileAdapter.ProfileViewHolder(itemView);
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvProfile;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            ivProfile = (ImageView) itemView.findViewById(R.id.iv_item_home);
            tvProfile = (TextView) itemView.findViewById(R.id.tv_item_home);
        }
    }


    @Override
    public void onBindViewHolder(ProfileAdapter.ProfileViewHolder holder, final int position) {
        holder.ivProfile.setImageResource(listProfile.get(position).getIconMenu());
        //Glide.with(context).load("https://drive.google.com/thumbnail?id="+linkIcon).into(holder.ivProfile);
        holder.tvProfile.setText(listProfile.get(position).getNamaMenu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        context.startActivity(new Intent(context, ListLamaranActivity.class));
                        break;
                    case 1:
                        Toast.makeText(context, "Maaf fitur ini belum aktif", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, "Maaf fitur ini belum aktif", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }


}
