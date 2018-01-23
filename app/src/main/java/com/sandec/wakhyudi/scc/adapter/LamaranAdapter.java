package com.sandec.wakhyudi.scc.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.sandec.wakhyudi.scc.Constans;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.model.Lamaran;
import com.sandec.wakhyudi.scc.model.Perusahaan;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wakhyudi on 23/01/18.
 */

public class LamaranAdapter extends RecyclerView.Adapter<LamaranAdapter.LamaranViewHolder> {

    private Context context;
    private List<Lamaran> listLamaran;

    public LamaranAdapter(Context context, List<Lamaran> listLamaran) {
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
        Button btnEditLamaran, btnDeleteLamaran;

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
        String linkIcon = listLamaran.get(position).getIconPerusahaan();
        Glide.with(context).load("https://drive.google.com/thumbnail?id=" + linkIcon).into(holder.ivLamaran);
        holder.tvLamaran.setText(listLamaran.get(position).getPerusahaanDilamar());

        holder.btnEditLamaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.update_lamaran_dialog, null);
                builder.setTitle("Update lamaran");
                builder.setView(view1);

                final EditText etNama = (EditText) view1.findViewById(R.id.et_update_nama_pelamar);
                final EditText etNik = (EditText) view1.findViewById(R.id.et_update_nik_pelamar);
                final EditText etCV = (EditText) view1.findViewById(R.id.et_update_cv_pelamar);
                final EditText etEmail = (EditText) view1.findViewById(R.id.et_update_email_pelamar);
                TextView tvPerusahaan = (TextView) view1.findViewById(R.id.tv_update_perusahaan_dilamar);
                Button btnSimpan = (Button) view1.findViewById(R.id.btn_update_lamaran);

                etNama.setText(listLamaran.get(position).getNamaPelamar());
                etNik.setText(listLamaran.get(position).getNikPelamar());
                etCV.setText(listLamaran.get(position).getCvPelamar());
                etEmail.setText(listLamaran.get(position).getEmailPelamar());
                tvPerusahaan.setText(listLamaran.get(position).getPerusahaanDilamar());

                final AlertDialog db = builder.create();
                db.show();

                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestQueue requestQueue = Volley.newRequestQueue(context);

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constans.baseUrl,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.isEmpty()) {
                                            Toast.makeText(context, "Update gagal", Toast.LENGTH_SHORT).show();
                                        }
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String hasil = jsonObject.getString("hasil");
                                            Toast.makeText(context, hasil, Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> sendData = new HashMap<>();
                                sendData.put("action", "update");
                                sendData.put("sheetName", "pelamar");
                                sendData.put("id", listLamaran.get(position).getIdPelamar());
                                sendData.put("name", etNama.getText().toString().trim());
                                sendData.put("nik", etNik.getText().toString().trim());
                                sendData.put("cv", etCV.getText().toString().trim());
                                sendData.put("email", etEmail.getText().toString().trim());
                                return sendData;
                            }
                        };

                        requestQueue.add(stringRequest);
                        db.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLamaran.size();
    }


}
