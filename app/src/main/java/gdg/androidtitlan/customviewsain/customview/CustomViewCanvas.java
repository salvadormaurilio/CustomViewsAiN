package gdg.androidtitlan.customviewsain.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gdg.androidtitlan.customviewsain.R;

/**
 * Created by Edgar Salvador Maurilio on 07/09/2015.
 */
public class CustomViewCanvas extends View {

    public static final String LOG_TAG = CustomViewCanvas.class.getSimpleName();


    private List<Path> paths;
    private Paint paintPaths;

    public CustomViewCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        paths = new ArrayList<>();

        TypedArray attributes = getAttrivutes(context, attrs);

        initPaint(getColorPath(attributes), getDimensionPath(attributes));

    }

    private TypedArray getAttrivutes(Context context, AttributeSet attrs) {
        return context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViewCanvas, 0, 0);
    }

    private int getColorPath(TypedArray attributes) {
        return attributes.getColor(R.styleable.CustomViewCanvas_llnea_color, Color.BLACK);
    }

    private float getDimensionPath(TypedArray attributes) {
        return attributes.getDimension(R.styleable.CustomViewCanvas_linea_width, 16);
    }

    private void initPaint(int colorPath, float withPath) {
        paintPaths = new Paint();

        paintPaths.setColor(colorPath);
        paintPaths.setStrokeWidth(withPath);
        paintPaths.setAntiAlias(true);
        paintPaths.setStyle(Paint.Style.STROKE);
        paintPaths.setStrokeJoin(Paint.Join.ROUND);
        paintPaths.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        drawPaths(canvas);
    }

    private void drawPaths(Canvas canvas) {
        for (Path path : paths) {
            canvas.drawPath(path, paintPaths);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addNewPath(event);
                break;
            case MotionEvent.ACTION_MOVE:
                addPoint(event);
                break;
        }
        return true;
    }

    private void addNewPath(MotionEvent event) {
        Path path = new Path();
        path.moveTo(event.getX(), event.getY());
        paths.add(path);
        invalidate();
    }

    private void addPoint(MotionEvent event) {
        Path path = paths.get(paths.size() - 1);
        path.lineTo(event.getX(), event.getY());
        invalidate();
    }



    public void clearCanvas() {
        Log.d(LOG_TAG, "clearCanvas");
        paths.clear();
        invalidate();
    }

}
