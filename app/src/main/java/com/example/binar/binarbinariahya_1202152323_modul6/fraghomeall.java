package com.example.binar.binarbinariahya_1202152323_modul6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class fraghomeall extends Fragment {
    //inisialisasi variabel yang dibutuhkan
    RecyclerView rv;
    DatabaseReference dataref;
    ArrayList<databasePost> list;
    adapterPost ap;


    public fraghomeall() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inisialisasi semua objek
        View v = inflater.inflate(R.layout.fragment_fraghomeall, container, false);
        rv = v.findViewById(R.id.rvhomeall);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        ap = new adapterPost(list, this.getContext());

        //menampilkan recyclerview
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(ap);

        //event listener ketika value pada firebase berubah
        dataref.addChildEventListener(new ChildEventListener() {
            //dipakai untuk membaca seluruh posting dari firebase
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                databasePost post = dataSnapshot.getValue(databasePost.class);
                post.key = dataSnapshot.getKey();
                list.add(post);
                ap.notifyDataSetChanged();

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
