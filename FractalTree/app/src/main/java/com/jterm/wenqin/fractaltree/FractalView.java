package com.jterm.wenqin.fractaltree;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by demouser on 1/13/17.
 */

public class FractalView extends View {
    private Paint mBackgroundPaint;
    private Paint mPaint;
    private int mMaxDepth;

    public FractalView(Context context) {
        super(context);
        init();
    }

    public FractalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FractalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FractalView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getResources().getColor(R.color.background_color));

        mPaint = new Paint();
        mPaint.setColor(Color.rgb(25, 50, 100));
        mPaint.setStrokeWidth(12);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == 0 || heightMeasureSpec == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int size = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawRect(0,0,getWidth(),getHeight(),mBackgroundPaint);

        drawFractalTree(canvas,getWidth()/2, getHeight(),90,mMaxDepth);

    }

    private void drawFractalTree(Canvas canvas, float x, float y, double angle, int depth){
        if (depth == 0){
            return;
        } else {
            float branchLength = depth * 8;
            float strokeWidth = depth;
            mPaint.setColor(Color.CYAN);
            mPaint.setStrokeWidth(strokeWidth);

            if (depth == mMaxDepth){
                canvas.drawLine(x,y,x,y-branchLength,mPaint);
                drawFractalTree(canvas,x,y-branchLength,90,depth-1);
            } else {
            double nextAngleLeft = angle + 20;
            double nextAngleRight = angle - 20;
            float xNextLeft = x + (float) (branchLength * Math.cos((Math.toRadians(nextAngleLeft))));
            float xNextRight = x + (float) (branchLength * Math.cos((Math.toRadians(nextAngleRight))));
            float yNextLeft = y - (float) (branchLength * Math.sin(Math.toRadians(nextAngleLeft)));
            float yNextRight = y - (float) (branchLength * Math.sin(Math.toRadians(nextAngleRight)));
            Log.d("Angle",String.valueOf(angle));
            canvas.drawLine(x, y, xNextLeft, yNextLeft, mPaint);
            canvas.drawLine(x, y, xNextRight, yNextRight, mPaint);
            drawFractalTree(canvas,xNextLeft,yNextLeft,nextAngleLeft,depth-1);
            drawFractalTree(canvas, xNextRight,yNextRight,nextAngleRight,depth-1);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void reset(int depth){
        mMaxDepth = depth;

        //redraw, friendly
        postInvalidateOnAnimation();
    }
}
