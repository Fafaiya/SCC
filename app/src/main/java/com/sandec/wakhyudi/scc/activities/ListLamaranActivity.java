package com.sandec.wakhyudi.scc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.adapter.LamaranAdapter;
import com.sandec.wakhyudi.scc.model.Lamaran;
import com.sandec.wakhyudi.scc.model.Perusahaan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListLamaranActivity extends AppCompatActivity {
    RecyclerView rvListLamaran;
    List<Lamaran> listLamaran = new ArrayList<>();
    String url = "https://script.google.com/macros/s/AKfycbyt6w414MNvYZEA_he5VJMGr50HfuUBAdwT0iXfR7zqOX1cgpsp/exec?action=read&sheetName=pelamar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lamaran);
        rvListLamaran = (RecyclerView) findViewById(R.id.rv_list_lamaran);
        rvListLamaran.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue requestKeServer = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.isEmpty()) {
                            Toast.makeText(ListLamaranActivity.this, "Tidak ada lamaran yang dikirim", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("pelamar");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dataLamaran = jsonArray.getJSONObject(i);
                                String id = dataLamaran.getString("id_pelamar");
                                String nikPelamar = dataLamaran.getString("nik_pelamar");
                                String namaPelamar = dataLamaran.getString("nama");
                                String cvPelamar = dataLamaran.getString("link_cv");
                                String emailPelamar = dataLamaran.getString("email");
                                String namaPerusahaan = dataLamaran.getString("perusahaan");
                                String iconPerusahaan = dataLamaran.getString("icon_perusahaan");

                                Lamaran lamaran = new Lamaran(id,nikPelamar,namaPelamar,cvPelamar,emailPelamar,namaPerusahaan,iconPerusahaan);
                                listLamaran.add(lamaran);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        LamaranAdapter adapter = new LamaranAdapter(ListLamaranActivity.this, listLamaran);
                        rvListLamaran.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListLamaranActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestKeServer.add(stringRequest);
    }
}
