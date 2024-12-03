package com.example.doodleapp11;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DoodleView doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doodleView = findViewById(R.id.doodleView);

        // Clear Button
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(view -> doodleView.clearCanvas());

        // Brush Size Button
        Button brushSizeButton = findViewById(R.id.brushSizeButton);
        brushSizeButton.setOnClickListener(view -> showBrushSizeDialog());

        // Color Picker Button
        Button colorPickerButton = findViewById(R.id.colorPickerButton);
        colorPickerButton.setOnClickListener(view -> showColorPickerDialog());

        // Opacity Button
        Button opacityButton = findViewById(R.id.opacityButton);
        opacityButton.setOnClickListener(view -> showOpacityDialog());
    }

    // Show dialog to choose brush size
    private void showBrushSizeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Brush Size");

        String[] sizes = {"Small", "Medium", "Large"};
        builder.setItems(sizes, (dialog, which) -> {
            switch (which) {
                case 0:
                    doodleView.setBrushSize(10); // Small
                    break;
                case 1:
                    doodleView.setBrushSize(20); // Medium
                    break;
                case 2:
                    doodleView.setBrushSize(30); // Large
                    break;
            }
        });

        builder.create().show();
    }

    // Show dialog to choose color
    private void showColorPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Color");

        String[] colors = {"Red", "Blue", "Green", "Black"};
        builder.setItems(colors, (dialog, which) -> {
            switch (which) {
                case 0:
                    doodleView.setBrushColor(Color.RED);
                    break;
                case 1:
                    doodleView.setBrushColor(Color.BLUE);
                    break;
                case 2:
                    doodleView.setBrushColor(Color.GREEN);
                    break;
                case 3:
                    doodleView.setBrushColor(Color.BLACK);
                    break;
            }
        });

        builder.create().show();
    }

    // Show dialog to adjust opacity
    private void showOpacityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adjust Opacity");

        SeekBar opacitySeekBar = new SeekBar(this);
        opacitySeekBar.setMax(255); // Max alpha value
        opacitySeekBar.setProgress(doodleView.getBrushOpacity()); // Set current opacity

        builder.setView(opacitySeekBar);
        builder.setPositiveButton("OK", (dialog, which) -> {
            int alpha = opacitySeekBar.getProgress();
            doodleView.setBrushOpacity(alpha);
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
