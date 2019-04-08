package com.mjacksi.album123.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjacksi.album123.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    // FOR DATA
    private List<Integer> images;

    // CONSTRUCTOR
    public AlbumAdapter(List<Integer> images) {
        this.images = images;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_list_item, parent, false);

        return new AlbumViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A Album
    @Override
    public void onBindViewHolder(AlbumViewHolder viewHolder, int position) {
        //viewHolder.updateWithImage(this.images.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.images.size();
    }
}