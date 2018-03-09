package com.zhongruan.android.zkfingerdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class UploadProgressBar extends ProgressBar {
    private Paint mPaint;
    private String text_progress;

    public UploadProgressBar(Context context) {
        super(context);
        initPaint();
    }

    public UploadProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public UploadProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        setTextProgress(progress);
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text_progress, 0, this.text_progress.length(), rect);
        canvas.drawText(this.text_progress, (float) ((getWidth() / 2) - rect.centerX()), (float) ((getHeight() / 2) - rect.centerY()), this.mPaint);
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize(23.0f);
        this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    private void setTextProgress(int progress) {
        this.text_progress = String.valueOf((int) (((((float) progress) * SimpleItemTouchHelperCallback.ALPHA_FULL) / ((float) getMax())) * 100.0f)) + "%";
    }
}
