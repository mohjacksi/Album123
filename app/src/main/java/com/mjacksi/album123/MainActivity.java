package com.mjacksi.album123;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void size15x15(View v) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.fragment_history_bottom_sheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();

        LinearLayout _14Picture = sheetView.findViewById(R.id.fragment_history_bottom_sheet_14);
        LinearLayout _16Picture = sheetView.findViewById(R.id.fragment_history_bottom_sheet_16);
        LinearLayout _18Picture = sheetView.findViewById(R.id.fragment_history_bottom_sheet_18);
        LinearLayout _20Picture = sheetView.findViewById(R.id.fragment_history_bottom_sheet_20);


        final Intent i = new Intent(MainActivity.this, Size15x15Activity.class);
        _14Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 14;
                //Toast.makeText(MainActivity.this, size, Toast.LENGTH_SHORT).show();
                i.putExtra("size", size);
                startActivity(i);
                mBottomSheetDialog.hide();
            }
        });

        _16Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 16;
                Toast.makeText(MainActivity.this, size, Toast.LENGTH_SHORT).show();
                i.putExtra("size", size);
                startActivity(i);
            }
        });

        _18Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 18;
                Toast.makeText(MainActivity.this, size, Toast.LENGTH_SHORT).show();
                i.putExtra("size", size);
                startActivity(i);
            }
        });
        _20Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 20;
                Toast.makeText(MainActivity.this, size, Toast.LENGTH_SHORT).show();
                i.putExtra("size", size);
                startActivity(i);
            }
        });

    }
}
