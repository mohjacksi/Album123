package com.mjacksi.album123;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Image> images;
    private LayoutInflater inflater;
    private int albumSize;


    private Size15x15Activity _size15x15Activity;

    public ImageAdapter(Context context, int albumSize, Size15x15Activity size15x15Activity) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        images = new ArrayList<>();
        this.albumSize = albumSize;
        this._size15x15Activity = size15x15Activity;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(inflater.inflate(R.layout.album_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        if (position < images.size()) {
            final Image image = images.get(position);

            Glide.with(context)
                    .load(image.getPath())
                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder))
                    .into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _size15x15Activity.editImageAt(position);
                }
            });
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_add_larg)
                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder))
                    .into(holder.imageView);
            holder.imageView.setBackgroundColor(context.getResources().getColor(R.color.imagepicker_pink));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _size15x15Activity.startPickPictures(albumSize);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (images.size() < albumSize) {
            return images.size() + 1;
        } else {
            return images.size();
        }
    }

    public void setData(List<Image> images) {
        this.images.clear();
        if (images != null) {
            this.images.addAll(images);
        }
        notifyDataSetChanged();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
