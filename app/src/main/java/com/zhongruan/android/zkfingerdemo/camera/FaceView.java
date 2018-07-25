package com.zhongruan.android.zkfingerdemo.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;


public class FaceView extends android.support.v7.widget.AppCompatImageView {
    private Paint mLinePaint;
    private int[] points = null;
    private Path mGraphic;
    private final Object mLock = new Object();
    private int x, y;

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public void setPoints(int[] points) {
        this.points = points;
        invalidate();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void clearFaces() {
        synchronized (this.mLock) {
            this.points = null;
        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0);
        synchronized (mLock) {
            canvas.save();
            if (points == null || points.length < 1) {
                return;
            }
            mGraphic.reset();
            float xRatio = (float) getWidth() / x;
            float yRatio = (float) getHeight() / y;
            mGraphic.moveTo(points[0] * xRatio, points[1] * yRatio);
            mGraphic.lineTo(points[2] * xRatio, points[3] * yRatio);
            mGraphic.lineTo(points[4] * xRatio, points[5] * yRatio);
            mGraphic.lineTo(points[6] * xRatio, points[7] * yRatio);
            mGraphic.lineTo(points[0] * xRatio, points[1] * yRatio);
            canvas.scale(-1, 1, getWidth() / 2, getHeight() / 2);
            canvas.save();
            mGraphic.close();//封闭
            canvas.drawPath(mGraphic, mLinePaint);
            canvas.restore();
            return;
        }
    }

    private void initPaint() {
        mGraphic = new Path();
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.argb(255, 249, 178, 67));
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setStrokeWidth(3.0f);
        mLinePaint.setAntiAlias(true);
    }
}
