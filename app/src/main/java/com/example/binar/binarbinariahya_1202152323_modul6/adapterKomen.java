package com.example.binar.binarbinariahya_1202152323_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by telkom on 31/03/2018.
 */

public class adapterKomen extends RecyclerView.Adapter<adapterKomen.CommentHolder>{
    Context con;
    List<databaseKomen> list;

    //constructor adapter
    public adapterKomen(Context con, List<databaseKomen> list) {
        this.con = con;
        this.list = list;
    }
    //return viewholder dari recycler view
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(con).inflate(R.layout.cv_comment, parent, false));
    }

    //mengambil nilai dari list dengan view
    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        databaseKomen cur = list.get(position);
        holder.sikomen.setText(cur.getSikomen());
        holder.komen.setText(cur.getKomen());

    }

    //mendapat jumlah item di recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }

    //subclass dari viewholder
    class CommentHolder extends RecyclerView.ViewHolder {
        TextView sikomen, komen;
        public CommentHolder(View itemView) {
            super(itemView);
            sikomen = itemView.findViewById(R.id.yangkomen);
            komen = itemView.findViewById(R.id.komen);
        }
    }
}
