package com.mjacksi.album123;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mjacksi.album123.Views.AlbumAdapter;
import com.mjacksi.album123.Views.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Size15x15Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Integer> photos;
    AlbumAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size15x15);
        final int albumSize = getIntent().getIntExtra("size", 14);

        photos = new ArrayList<>();
        for (int i = 0; i < albumSize; i++) {
            photos.add(R.drawable.ic_perm_media_black_24dp);
        }
        adapter = new AlbumAdapter(photos);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //define span size for this position
                //some example for your first three items
//                if(position == 0) {
//                    return 2; //item will take 1/3 space of row
//                }
                return 1;
            }
        });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(photos, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(photos, i, i - 1);
                    }
                }

                //Collections.swap(Hunts, viewHolder.getAdapterPosition(), target.getAdapterPosition());

                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // no-op
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyclerView);


        recyclerView.addItemDecoration(new ItemOffsetDecoration(
                getResources().getDimensionPixelSize(R.dimen.photos_list_spacing)));


    }
}
