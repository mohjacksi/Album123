package com.mjacksi.album123.Views;

import android.view.View;
import android.widget.ImageView;

import com.mjacksi.album123.R;

import androidx.recyclerview.widget.RecyclerView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        itemView.findViewById(R.id.image_view);
    }

    public void updateWithImage(int resId) {
        this.imageView.setImageResource(resId);
    }
}