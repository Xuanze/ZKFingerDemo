package com.zhongruan.android.zkfingerdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhongruan.android.zkfingerdemo.R;

import java.util.Timer;
import java.util.TimerTask;

import rx.android.BuildConfig;

public class NumberInputView extends View {
    private float TEXTSIZE;
    private Paint bitmapPaint;
    private boolean canDelect;
    private Bitmap deleteBitmap;
    private int height;
    public String inputStr;
    private boolean isLongClick;
    private boolean isTouch;
    private Handler mHandler;
    private int mHeight;
    private Paint mPaint;
    private int mWidth;
    TimerTask task;
    private String[] text;
    private Paint textPaint;
    private Timer timer;
    private OnTouchListener touchListener;
    private int touchX;
    private int touchY;
    private int width;

    public interface OnTouchListener {
        void getString(String str);
    }

    private void delectStr() {
        if (this.inputStr.length() > 0) {
            this.inputStr = this.inputStr.substring(0, this.inputStr.length() - 1);
        } else {
            this.inputStr = BuildConfig.VERSION_NAME;
        }
        if (this.touchListener != null) {
            this.touchListener.getString(this.inputStr);
        }
    }

    public NumberInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.text = new String[]{BuildConfig.VERSION_NAME, "1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "0", BuildConfig.VERSION_NAME};
        this.inputStr = BuildConfig.VERSION_NAME;
        this.TEXTSIZE = 30.0f;
        this.isTouch = false;
        this.isLongClick = false;
        this.canDelect = false;
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        NumberInputView.this.delectStr();
                    default:
                }
            }
        };
        this.task = new TimerTask() {
            public void run() {
                if (NumberInputView.this.canDelect) {
                    Message message = new Message();
                    message.what = 1;
                    NumberInputView.this.mHandler.sendMessage(message);
                }
            }
        };
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.argb(255, 216, 216, 216));
        this.mPaint.setStrokeWidth(SimpleItemTouchHelperCallback.ALPHA_FULL);
        this.textPaint = new Paint();
        this.textPaint.setStrokeWidth(5.0f);
        this.textPaint.setColor(Color.argb(255, 0, 0, 0));
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize(this.TEXTSIZE);
        this.textPaint.setTextAlign(Align.CENTER);
        this.textPaint.setTypeface(Typeface.MONOSPACE);
        this.bitmapPaint = new Paint();
        this.bitmapPaint.setStrokeWidth(2.0F);
        this.bitmapPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.deleteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_base_keyboard_delete_img);
        this.inputStr = BuildConfig.VERSION_NAME;
        this.isTouch = false;
        this.timer = new Timer();
        this.timer.schedule(this.task, 100, 100);
    }

    public NumberInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.text = new String[]{BuildConfig.VERSION_NAME, "1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "0", BuildConfig.VERSION_NAME};
        this.inputStr = BuildConfig.VERSION_NAME;
        this.TEXTSIZE = 30.0f;
        this.isTouch = false;
        this.isLongClick = false;
        this.canDelect = false;
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        NumberInputView.this.delectStr();
                    default:
                }
            }
        };
        this.task = new TimerTask() {
            public void run() {
                if (NumberInputView.this.canDelect) {
                    Message message = new Message();
                    message.what = 1;
                    NumberInputView.this.mHandler.sendMessage(message);
                }
            }
        };
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    public void onDraw(Canvas canvas) {
        int i;
        int j;
        this.mWidth = this.width / 3;
        this.mHeight = this.height / 4;
        canvas.drawColor(-1);
        this.mPaint.setColor(Color.argb(255, 216, 216, 216));
        canvas.drawLine(SimpleItemTouchHelperCallback.ALPHA_FULL, SimpleItemTouchHelperCallback.ALPHA_FULL, SimpleItemTouchHelperCallback.ALPHA_FULL, (float) this.height, this.mPaint);
        for (i = 1; i <= 3; i++) {
            canvas.drawLine((float) (this.mWidth * i), 0.0f, (float) (this.mWidth * i), (float) this.height, this.mPaint);
        }
        for (j = 0; j <= 4; j++) {
            canvas.drawLine(0.0f, (float) (this.mHeight * j), (float) this.width, (float) (this.mHeight * j), this.mPaint);
        }
        for (i = 1; i <= 3; i++) {
            for (j = 0; j < 4; j++) {
                canvas.drawText(this.text[(j * 3) + i], (float) ((this.mWidth * i) - (this.mWidth / 2)), ((float) (((j + 1) * this.mHeight) - (this.mHeight / 2))) + (this.TEXTSIZE / 2.0F), this.textPaint);
            }
        }
        canvas.drawRect((float) (this.mWidth * 2), (float) (this.mHeight * 3), (float) (this.mWidth * 3), (float) (this.mHeight * 4), this.mPaint);
        Matrix matrix = new Matrix();
        matrix.setScale(0.45f, 0.45f);
        Bitmap dtbmp = Bitmap.createBitmap(this.deleteBitmap, 0, 0, this.deleteBitmap.getWidth(), this.deleteBitmap.getHeight(), matrix, true);
        canvas.drawBitmap(dtbmp, (float) (((this.mWidth * 5) / 2) - (dtbmp.getWidth() / 2)), (float) (((this.mHeight * 7) / 2) - (dtbmp.getHeight() / 2)), this.bitmapPaint);
        this.mPaint.setColor(Color.argb(100, 0, 0, 0));
        if (this.isTouch) {
            canvas.drawRect((float) ((this.mWidth * this.touchX) - this.mWidth), (float) (this.mHeight * this.touchY), (float) (this.mWidth * this.touchX), (float) ((this.mHeight * this.touchY) + this.mHeight), this.mPaint);
        }
    }

    private void delectLongClick() {
        if (this.isLongClick) {
            this.canDelect = true;
        } else {
            this.canDelect = false;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.touchX = ((int) (event.getX() / ((float) this.mWidth))) + 1;
                this.touchY = (int) (event.getY() / ((float) this.mHeight));
                if ((this.touchY * 3) + this.touchX >= 0) {
                    if (this.text[(this.touchY * 3) + this.touchX].equals(BuildConfig.VERSION_NAME)) {
                        if (this.inputStr.length() > 0) {
                            this.inputStr = this.inputStr.substring(0, this.inputStr.length() - 1);
                            this.isLongClick = true;
                            postDelayed(new Runnable() {
                                public void run() {
                                    NumberInputView.this.delectLongClick();
                                }
                            }, 500);
                        } else {
                            this.inputStr = BuildConfig.VERSION_NAME;
                        }
                    } else if (this.inputStr.length() < 18) {
                        this.inputStr += this.text[(this.touchY * 3) + this.touchX];
                    }
                    if (this.touchListener != null) {
                        this.touchListener.getString(this.inputStr);
                    }
                    this.isTouch = true;
                    break;
                }
                this.isTouch = true;
                break;
            case 1:
                this.isTouch = false;
                if (this.isLongClick) {
                    this.isLongClick = false;
                    delectLongClick();
                    break;
                }
                break;
        }
        invalidate();
        return true;
    }

    public void onTouchListener(OnTouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public void cleanInputStr() {
        if (this.inputStr != null) {
            this.inputStr = BuildConfig.VERSION_NAME;
        }
    }
}
