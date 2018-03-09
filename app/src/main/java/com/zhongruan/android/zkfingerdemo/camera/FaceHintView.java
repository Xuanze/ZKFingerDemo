package com.zhongruan.android.zkfingerdemo.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;

public class FaceHintView extends android.support.v7.widget.AppCompatImageView {
    private Paint mLinePaint;
    private RectF mRect;

    public FaceHintView(Context context) {
        super(context);
        this.mRect = new RectF();
        initPaint();
        invalidate();
    }

    public FaceHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRect = new RectF();
        initPaint();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float hintW = (((float) height) / 240.0f) * 168.0f;
        this.mRect.bottom = (float) (height - 4);
        this.mRect.top = 4.0f;
        this.mRect.left = (((float) width) - hintW) / 2.0f;
        this.mRect.right = this.mRect.left + hintW;
        canvas.drawRect(this.mRect, this.mLinePaint);
        canvas.restore();
        super.onDraw(canvas);
    }

    private void initPaint() {
        this.mLinePaint = new Paint(1);
        this.mLinePaint.setColor(Color.green(-16711936));
        this.mLinePaint.setStyle(Style.STROKE);
        this.mLinePaint.setPathEffect(new DashPathEffect(new float[]{3.0f, 2.0f}, 0.0f));
        this.mLinePaint.setStrokeWidth(4.0f);
        this.mLinePaint.setAlpha(180);
    }
}