package com.zhongruan.android.zkfingerdemo.camera;

import android.content.Context;
import android.hardware.Camera.CameraInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "HNZR";
    private Context mContext;
    private SurfaceHolder mSurfaceHolder;

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mSurfaceHolder = getHolder();
        this.mSurfaceHolder.setFormat(-2);
        this.mSurfaceHolder.setType(3);
        this.mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceCreated...");
        CameraInterface.getInstance().doOpenCamera(null, CameraInfo.CAMERA_FACING_BACK);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceChanged...");
        CameraInterface.getInstance().doStartPreview(mSurfaceHolder, 1.333f, 0);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceDestroyed...");
        CameraInterface.getInstance().doStopCamera();
    }
}
