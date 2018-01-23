package com.sandec.wakhyudi.scc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sandec.wakhyudi.scc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailLowonganActivity extends AppCompatActivity {
    Bundle b;
    ImageView ivPerusahaan;
    TextView tvnamaPerusahaan, tvNamaPerusahaanKirim, tvDeskripsiLamaran;
    Button btnDaftar;
    EditText etNama, etNik, etEmail, etCV;
    LinearLayout llLamaran;
    String namaPerusahaan, nama, nik, email, cv, iconPerusahaan;
    String url = "https://script.google.com/macros/s/AKfycbyt6w414MNvYZEA_he5VJMGr50HfuUBAdwT0iXfR7zqOX1cgpsp/exec";
    String hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lowongan);
        ivPerusahaan = (ImageView) findViewById(R.id.iv_detail_lowongan);
        tvnamaPerusahaan = (TextView) findViewById(R.id.tv_detail_lowongan);
        tvDeskripsiLamaran = (TextView) findViewById(R.id.tv_detail_deskripsi);
        btnDaftar = (Button) findViewById(R.id.btn_detail_daftar);
        tvNamaPerusahaanKirim = (TextView) findViewById(R.id.tv_detail_perusahaan_dilamar);
        etNama = (EditText) findViewById(R.id.et_detail_nama_pelamar);
        etNik = (EditText) findViewById(R.id.et_detail_nik_pelamar);
        etEmail = (EditText) findViewById(R.id.et_detail_email_pelamar);
        etCV = (EditText) findViewById(R.id.et_detail_cv_pelamar);
        llLamaran = (LinearLayout) findViewById(R.id.ll_lamaran);
        b = getIntent().getExtras();

        iconPerusahaan = b.getString("iconPerusahaan");
        namaPerusahaan = b.getString("perusahaan");
        String deskripsiLamaran = b.getString("deskripsiPerusahaan");


        Glide.with(this).load("https://drive.google.com/thumbnail?id=" + iconPerusahaan).into(ivPerusahaan);
        tvnamaPerusahaan.setText(namaPerusahaan);
        tvDeskripsiLamaran.setText(deskripsiLamaran);
    }

    public void daftarKerja(View view) {
        llLamaran.setVisibility(View.VISIBLE);
        btnDaftar.setVisibility(View.GONE);
        tvNamaPerusahaanKirim.setText(namaPerusahaan);
    }

    public void kirimLamaran(View view) {
        validasi();
        nama = etNama.getText().toString().trim();
        nik = etNik.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        cv = etCV.getText().toString().trim();


        RequestQueue requestKeServer = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.isEmpty()) {
                            Toast.makeText(DetailLowonganActivity.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            hasil = jsonObject.getString("hasil");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(DetailLowonganActivity.this, hasil, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailLowonganActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> sendData = new HashMap<>();
                sendData.put("action", "insert");
                sendData.put("sheetName", "pelamar");
                sendData.put("name", nama);
                sendData.put("nik", nik);
                sendData.put("email", email);
                sendData.put("cv", cv);
                sendData.put("perusahaan", namaPerusahaan);
                sendData.put("icon", iconPerusahaan);
                return sendData;

            }
        };

        requestKeServer.add(stringRequest);
    }

    public void validasi() {
        if (etNama.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etNik.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etCV.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
    }


}
