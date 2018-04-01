package com.example.binar.binarbinariahya_1202152323_modul6;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class post extends AppCompatActivity {
    TextView user, judul, caption;
    ImageView image;
    EditText komentar;
    RecyclerView rv;
    adapterKomen adapter;
    ArrayList<databaseKomen> list;
    DatabaseReference dref;
    ProgressDialog pd;
    String usernya, idfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Binstagram");

        user = findViewById(R.id.yangupload);
        image = findViewById(R.id.gambardaripost);
        komentar = findViewById(R.id.srckomentar);
        pd = new ProgressDialog(this);
        judul = findViewById(R.id.judulgambarpost);
        caption = findViewById(R.id.deskripsigambarpost);
        dref = FirebaseDatabase.getInstance().getReference().child("comment");
        rv = findViewById(R.id.rvkomentar);
        list = new ArrayList<>();
        adapter = new adapterKomen(this, list);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        String [] usernow = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@");
        usernya = usernow[0];
        idfoto = getIntent().getStringExtra("key");
        user.setText(getIntent().getStringExtra("user"));
        judul.setText(getIntent().getStringExtra("judul"));
        caption.setText(getIntent().getStringExtra("caption"));
        Glide.with(this).load(getIntent().getStringExtra("image")).override(250,250).into(image);

        dref.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    databaseKomen cur = dataSnapshot.getValue(databaseKomen.class);
                    if (cur.getFotokomen().equals(idfoto)) {
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
    }

    public void postcomment(View view) {
        pd.setMessage("Adding comment");
        pd.show();

        databaseKomen com = new databaseKomen(usernya, komentar.getText().toString(), idfoto);
        dref.push().setValue(com).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(post.this, "Comment ditambahkan", Toast.LENGTH_SHORT).show();
                    komentar.setText(null);
                }else {
                    Toast.makeText(post.this, "Comment gagal ditambahkan", Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();
            }
        });
    }
}
