package com.mjacksi.album123.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mjacksi.album123.R;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    // FOR DATA
    private List<Image> images;
    private Context context;
    // CONSTRUCTOR
    public AlbumAdapter(List<Image> images, Context context) {
        this.images = images;
        this.context = context;
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
        final Image image = images.get(position);
        Glide.with(context)
                .load(image.getPath())
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder))
                .into(viewHolder.imageView);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.images.size();
    }

    public void setData(List<Image> images) {
        this.images.clear();
        if (images != null) {
            this.images.addAll(images);
        }
        notifyDataSetChanged();
    }
}