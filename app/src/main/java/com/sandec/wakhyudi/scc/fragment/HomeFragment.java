package com.sandec.wakhyudi.scc.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.adapter.PerusahaanAdapter;
import com.sandec.wakhyudi.scc.model.Perusahaan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView rvHome;
    List<Perusahaan> listPerusahaan = new ArrayList<>();
    String url = "https://script.google.com/macros/s/AKfycbyt6w414MNvYZEA_he5VJMGr50HfuUBAdwT0iXfR7zqOX1cgpsp/exec?action=read&sheetName=daftar_perusahaan";
    String idPerusahaan, namaPerusahaan, logoPerusahaan,deskripsi;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        rvHome.setLayoutManager(glm);

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Load data ...");
        pd.show();

        RequestQueue requestKeServer = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("daftar_perusahaan");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                idPerusahaan = data.getString("id_perusahaan");
                                namaPerusahaan = data.getString("nama_perusahaan");
                                logoPerusahaan = data.getString("logo_perusahaan");
                                deskripsi = data.getString("deskripsi");

                                Perusahaan perusahaan = new Perusahaan(namaPerusahaan, logoPerusahaan,deskripsi);
                                listPerusahaan.add(perusahaan);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PerusahaanAdapter adapter = new PerusahaanAdapter(getActivity(), listPerusahaan);
                        rvHome.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestKeServer.add(stringRequest);

        return view;
    }

}
