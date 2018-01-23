package com.sandec.wakhyudi.scc.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandec.wakhyudi.scc.R;
import com.sandec.wakhyudi.scc.adapter.ProfileAdapter;
import com.sandec.wakhyudi.scc.model.Perusahaan;
import com.sandec.wakhyudi.scc.model.Profile;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
RecyclerView rvProfile;
    List<Profile>listProfile = new ArrayList<>();
    String [] namaProfile = {"Status Lamaran","Wawancara", "Pengaturan"};
    int[] iconProfile = {R.drawable.ic_status_lamaran,R.drawable.ic_wawancara,R.drawable.ic_pengaturan};
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        rvProfile = (RecyclerView)view.findViewById(R.id.rv_profile);
        rvProfile.setLayoutManager(new GridLayoutManager(getContext(),2));

        for (int i = 0; i <namaProfile.length ; i++) {
            Profile profile = new Profile(namaProfile[i],iconProfile[i]);
            listProfile.add(profile);

        }
        ProfileAdapter adapter = new ProfileAdapter(getActivity(),listProfile);
        rvProfile.setAdapter(adapter);
        return view;
    }

}
