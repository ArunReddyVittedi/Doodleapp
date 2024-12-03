package com.example.doodleapp11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class DoodleView extends View {
    private Paint paint;
    private Path path;
    private List<Path> paths;
    private List<Paint> paints;
    private int currentOpacity = 255; // Default full opacity

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF000000); // Default black
        paint.setStrokeWidth(10); // Default brush size
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setAlpha(currentOpacity); // Set initial opacity

        path = new Path();
        paths = new ArrayList<>();
        paints = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
        canvas.drawPath(path, paint); // Draw the current path
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                paths.add(path);
                Paint newPaint = new Paint(paint); // Clone current paint
                paints.add(newPaint);
                path = new Path(); // Start a new path
                invalidate();
                break;
        }
        return true;
    }

    // Clear the canvas
    public void clearCanvas() {
        paths.clear();
        paints.clear();
        path.reset();
        invalidate();
    }

    // Set brush size
    public void setBrushSize(float size) {
        paint.setStrokeWidth(size);
    }

    // Set brush color
    public void setBrushColor(int color) {
        paint.setColor(color);
    }

    // Set brush opacity
    public void setBrushOpacity(int alpha) {
        currentOpacity = alpha;
        paint.setAlpha(alpha);
    }

    // Get current opacity
    public int getBrushOpacity() {
        return currentOpacity;
    }
}
