package com.mjacksi.album123;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mjacksi.album123.Views.ItemOffsetDecoration;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class Size15x15Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    //AlbumAdapter adapter;
    ImageAdapter imageAdapter;
    int albumSize = 14;
    private ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size15x15);


        albumSize = getIntent().getIntExtra("size", 14);

        images = new ArrayList<>();
//        for (int i = 0; i < albumSize; i++) {
//            images.add(new Image(123,"h",""));
//        }
        //adapter = new AlbumAdapter(images,this);
        imageAdapter = new ImageAdapter(this, albumSize, Size15x15Activity.this);

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

                // because of the last cell will be a button to add images
                // if the number of images isn't reach the albumSize
                if (fromPosition < images.size() && toPosition < images.size()) {
                    if (fromPosition < toPosition) {
                        for (int i = fromPosition; i < toPosition; i++) {
                            Collections.swap(images, i, i + 1);
                        }
                    } else {
                        for (int i = fromPosition; i > toPosition; i--) {
                            Collections.swap(images, i, i - 1);
                        }
                    }
                    imageAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                    return true;

                } else {
                    return false;
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // no-op
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int fromPosition = viewHolder.getAdapterPosition();

                // every image inside the list will be draggable, the addisional cell
                // for add more images will be un-draggable in else!
                if (fromPosition < images.size()) {
                    return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                            ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
                } else {
                    return 0;  // to make add button in the end of list un-draggable
                }
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        touchHelper.attachToRecyclerView(recyclerView);


        recyclerView.addItemDecoration(new ItemOffsetDecoration(
                getResources().getDimensionPixelSize(R.dimen.photos_list_spacing)));

        startPickPictures(albumSize);
    }


    void startPickPictures(int numberOfImages) {

        // new ImagePicker.Builder(this).galleryOnly().start();

        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
                .setToolbarColor("#212121")         //  Toolbar color
                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#212121")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(true)              //  Select multiple images or single image
                .setFolderMode(false)                //  Folder mode
                .setShowCamera(true)                //  Show camera button
                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
                .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
                .setDoneTitle("Done")               //  Done button title
                .setLimitMessage("You have reached selection limit")    // Selection limit message
                .setMaxSize(albumSize)                     //  Max images can be selected
                .setSavePath("ImagePicker")         //  Image capture folder name
                .setSelectedImages(images)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .setAlwaysShowDoneButton(false)
                .start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            imageAdapter.setData(images);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void add_images(View view) {
        startPickPictures(albumSize);
    }

    public void editImageAt(int position) {

    }
}
