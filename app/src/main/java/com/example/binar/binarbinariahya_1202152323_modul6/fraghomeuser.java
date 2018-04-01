package com.example.binar.binarbinariahya_1202152323_modul6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fraghomeuser extends Fragment {
    //inisialisasi variabel
    RecyclerView rv;
    DatabaseReference ref;
    ArrayList<databasePost> list;
    adapterPost adapter;


    public fraghomeuser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inisialisasi semua objek pada database
        View v = inflater.inflate(R.layout.fragment_fraghomeuser, container, false);
        ref = FirebaseDatabase.getInstance().getReference().child("post");
        list = new ArrayList<>();
        adapter = new adapterPost(list, this.getContext());
        rv = v.findViewById(R.id.rvhomeuser);

        //menampilkan recyclerview
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);

        //eventlistener saat data pada firebase berubah
        ref.addChildEventListener(new ChildEventListener() {
            //untuk membaca posting user dari firebase
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                databasePost cur = dataSnapshot.getValue(databasePost.class);
                if (cur.getUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                    cur.key = dataSnapshot.getKey();
                    list.add(cur);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
