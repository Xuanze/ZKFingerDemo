package com.zhongruan.android.zkfingerdemo.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.camera.CameraInterface;
import com.zhongruan.android.zkfingerdemo.camera.CameraSurfaceView;
import com.zhongruan.android.zkfingerdemo.camera.util.DisplayUtil;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks_cjxx;
import com.zhongruan.android.zkfingerdemo.dialog.EditDialog;
import com.zhongruan.android.zkfingerdemo.dialog.HintDialog;
import com.zhongruan.android.zkfingerdemo.dialog.SelectDialog;
import com.zhongruan.android.zkfingerdemo.fingerprintengine.FingerData;
import com.zhongruan.android.zkfingerdemo.idcardengine.IDCardData;
import com.zhongruan.android.zkfingerdemo.utils.Base64Util;
import com.zhongruan.android.zkfingerdemo.utils.DateUtil;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;
import com.zhongruan.android.zkfingerdemo.utils.IDCard;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;
import com.zkteco.android.biometric.core.utils.ToolUtils;
import com.zkteco.android.biometric.module.fingerprint.FingerprintCaptureListener;
import com.zkteco.android.biometric.module.fingerprint.exception.FingerprintSensorException;

import java.util.List;

public class CJActivity extends BaseActivity implements OnClickListener, FingerprintCaptureListener {
    private LinearLayout llBack, llIdcard, llFace, llSwitchView, llSwitch, llPreCamera, include_face, include_finger, include_idcard;
    private RelativeLayout rlFingerProcess, rlTakeCamera;
    private ImageView ivIdFace, ivLeftFinger, ivRightFinger, ivFingerOk, ivFace, ivFinger, ivPrePic;
    private TextView tvTitle, tvCollectNum, tvIdMsg, tvName, tvSex, tvCardId, tvFingerMsg, tvInputIdCard, tvPrePic, tvFaceMsg, tvFingerTips, tv_quality;
    private Button btnUsePic, shutterBtn;
    private String et_input;
    private CameraSurfaceView surfaceView = null;
    private IDCardData idCardData;
    private FingerData fingerData;
    private Bitmap zwBitmap;
    private String cjSfzPath = "kscj_sfz";
    private String cjZwPath = "kscj_zw";
    private String cjXpPath = "kscj_xp";
    private Handler handler = new Handler();
    private boolean isCJ = false;
    private List<Bk_ks_cjxx> bkKsCjxxList;
    private int finger = 6;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_cj);
        startIDCardReader();
        startFingerprintSensor();
    }

    @Override
    public void initViews() {
        surfaceView = findViewById(R.id.camera_surfaceview);
        shutterBtn = findViewById(R.id.btn_takePic);
        llBack = findViewById(R.id.ll_back);
        tvTitle = findViewById(R.id.tv_title);
        tvCollectNum = findViewById(R.id.tv_collectNum);
        tvIdMsg = findViewById(R.id.tv_idMsg);
        llIdcard = findViewById(R.id.ll_idcard);
        ivIdFace = findViewById(R.id.iv_idFace);
        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvCardId = findViewById(R.id.tv_cardId);
        tvFingerMsg = findViewById(R.id.tv_fingerMsg);
        rlFingerProcess = findViewById(R.id.rl_fingerProcess);
        ivLeftFinger = findViewById(R.id.iv_leftFinger);
        ivRightFinger = findViewById(R.id.iv_rightFinger);
        ivFingerOk = findViewById(R.id.iv_fingerOk);
        tvFaceMsg = findViewById(R.id.tv_faceMsg);
        llFace = findViewById(R.id.ll_face);
        ivFace = findViewById(R.id.iv_face);
        llPreCamera = findViewById(R.id.ll_preCamera);
        rlTakeCamera = findViewById(R.id.rl_takeCamera);
        llSwitch = findViewById(R.id.ll_switch);
        tvPrePic = findViewById(R.id.tv_prePic);
        ivPrePic = findViewById(R.id.iv_prePic);
        btnUsePic = findViewById(R.id.btn_usePic);
        tvFingerTips = findViewById(R.id.tv_fingerTips);
        ivFinger = findViewById(R.id.iv_finger);
        llSwitchView = findViewById(R.id.ll_switchView);
        tvInputIdCard = findViewById(R.id.tv_inputIdCard);
        include_face = findViewById(R.id.include_face);
        include_finger = findViewById(R.id.include_finger);
        include_idcard = findViewById(R.id.include_idcard);
        tv_quality = findViewById(R.id.tv_quality);
    }

    @Override
    public void initListeners() {
        llBack.setOnClickListener(this);
        shutterBtn.setOnClickListener(this);
        btnUsePic.setOnClickListener(this);
        tvInputIdCard.setOnClickListener(this);
        llSwitchView.setOnClickListener(this);
    }

    @Override
    public void initData() {
        bkKsCjxxList = DbServices.getInstance(this).loadAllNote();
        tvCollectNum.setText("已采集：" + bkKsCjxxList.size());
        include_idcard.setVisibility(View.VISIBLE);
        tvTitle.setText("采集身份证");
        if (idCardData == null) {
            handler.postDelayed(runnable01, 100);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_inputIdCard:
                handler.removeCallbacks(runnable01); //停止刷新
                new EditDialog(CJActivity.this, R.style.dialog, new EditDialog.OnEditInputFinishedListener() {
                    @Override
                    public void editInputFinished(Dialog dialog, String password, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                            IDCard idCard = new IDCard();
                            if (idCard.validate_effective(password) == password) {
                                bkKsCjxxList = DbServices.getInstance(getBaseContext()).querySfzh(password);
                                et_input = password;
                                if (bkKsCjxxList.size() > 0) {
                                    new SelectDialog(CJActivity.this, R.style.dialog, "该考生已采集过特征，重复采集会覆盖上一次采集的数据，是否覆盖采集信息？", new SelectDialog.OnCloseListener() {
                                        @Override
                                        public void onClick(Dialog dialog, boolean confirm) {
                                            if (confirm) {
                                                DbServices.getInstance(getBaseContext()).deleteNote(bkKsCjxxList.get(0).getId());
                                                if (bkKsCjxxList.get(0).getRl_picpath() != null) {
                                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getRl_picpath());
                                                }
                                                if (bkKsCjxxList.get(0).getZw_picpath() != null) {
                                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getZw_picpath());
                                                }
                                                if (bkKsCjxxList.get(0).getSfz_picpath() != null) {
                                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getSfz_picpath());
                                                }
                                                getWIdCard();
                                                OnBnBegin();
                                                dialog.dismiss();
                                            } else {
                                                idCardData = null;
                                                if (idCardData == null) {
                                                    handler.postDelayed(runnable01, 100);
                                                }
                                            }
                                        }
                                    }).setTitle("提示").show();
                                } else {
                                    getWIdCard();
                                    OnBnBegin();
                                }
                            } else {
                                ShowToast("输入身份证号有误！");
                            }
                        } else {
                            dialog.dismiss();
                            handler.postDelayed(runnable01, 100);
                        }
                    }
                }).setInputType(InputType.TYPE_CLASS_NUMBER).setTitle("请输入身份证号").show();
                break;
            case R.id.ll_back:
                if (!isCJ) {
                    finish();
                } else {
                    isFinish();
                }
                break;
            case R.id.ll_switchView:
                if (finger == 6) {
                    tvFingerTips.setText("请按右手中指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger7);
                    finger = 7;
                } else if (finger == 7) {
                    tvFingerTips.setText("请按右手无名指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger8);
                    finger = 8;
                } else if (finger == 8) {
                    tvFingerTips.setText("请按右手尾指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger9);
                    finger = 9;
                } else if (finger == 9) {
                    tvFingerTips.setText("请按左手尾指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger0);
                    finger = 0;
                } else if (finger == 0) {
                    tvFingerTips.setText("请按左手无名指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger1);
                    finger = 1;
                } else if (finger == 1) {
                    tvFingerTips.setText("请按左手中指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger2);
                    finger = 2;
                } else if (finger == 2) {
                    tvFingerTips.setText("请按左手食指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger3);
                    finger = 3;
                } else if (finger == 3) {
                    tvFingerTips.setText("请按左手大拇指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger4);
                    finger = 4;
                } else if (finger == 4) {
                    tvFingerTips.setText("请按右手大拇指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger5);
                    finger = 5;
                } else if (finger == 5) {
                    tvFingerTips.setText("请按右手食指");
                    ivFinger.setBackgroundResource(R.drawable.img_module_tab_collect_dynamicflow_finger6);
                    finger = 6;
                }
                break;
            case R.id.btn_takePic:
                doTakePicture();
                break;
            case R.id.ll_switch:
                CameraInterface.getInstance().cameraSwitch();
                break;
            case R.id.btn_usePic:
                new Runnable() {
                    @Override
                    public void run() {
                        tvFaceMsg.setVisibility(View.GONE);
                        llFace.setVisibility(View.VISIBLE);
                        ivFace.setImageBitmap(CameraInterface.getInstance().rectBitmap);
                        if (idCardData.getMap() != null) {
                            FileUtils.saveBitmap(idCardData.getMap(), cjSfzPath, idCardData.getSfzh());
                        }
                        FileUtils.saveBitmap(zwBitmap, cjZwPath, idCardData.getSfzh() + "_" + finger);
                        FileUtils.saveBitmap(CameraInterface.getInstance().rectBitmap, cjXpPath, idCardData.getSfzh());
                        DbServices.getInstance(getBaseContext()).saveNote(idCardData.getCardNo(),
                                idCardData.getXm(),
                                idCardData.getXb(),
                                idCardData.getMz(),
                                idCardData.getBirth(),
                                idCardData.getAddress(),
                                idCardData.getSfzh(),
                                idCardData.getMap() != null ? cjSfzPath + "/" + idCardData.getSfzh() + ".jpg" : "", idCardData.getQfjg(),
                                idCardData.getYxjsrq(),
                                idCardData.getYxksrq(),
                                cjZwPath + "/" + idCardData.getSfzh() + "_" + finger + ".jpg",
                                Base64Util.encode(fingerData.getFingerFeatures()), fingerData.getQuality(),
                                cjXpPath + "/" + idCardData.getSfzh() + ".jpg",
                                DateUtil.getNowTime(),
                                0);
                    }
                }.run();
                new HintDialog(this, R.style.dialog, "采集信息完成", new HintDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            tvPrePic.setVisibility(View.VISIBLE);
                            ivPrePic.setVisibility(View.GONE);
                            rlTakeCamera.setVisibility(View.GONE);
                            llPreCamera.setVisibility(View.VISIBLE);
                            btnUsePic.setEnabled(false);
                            isCJ = false;
                            idCardData = null;
                            fingerData = null;
                            bkKsCjxxList = DbServices.getInstance(getBaseContext()).loadAllNote();
                            tvCollectNum.setText("已采集：" + bkKsCjxxList.size());
                            include_face.setVisibility(View.GONE);
                            include_idcard.setVisibility(View.VISIBLE);
                            llIdcard.setVisibility(View.GONE);
                            tvIdMsg.setVisibility(View.VISIBLE);
                            rlFingerProcess.setVisibility(View.GONE);
                            tvFingerMsg.setVisibility(View.VISIBLE);
                            llFace.setVisibility(View.GONE);
                            tvFaceMsg.setVisibility(View.VISIBLE);
                            handler.postDelayed(runnable01, 100);
                            dialog.dismiss();
                        }
                    }
                }).setTitle("提示").setLLButtonVisibility(true).setBackgroundResource(R.drawable.img_base_check).show();
                break;
            default:
                break;
        }
    }

    private Runnable runnable01 = new Runnable() {
        public void run() {
            idCardData = OnBnRead();
            if (idCardData != null) {
                soundPool.play(musicId.get(1), 1, 1, 0, 0, 1);
                bkKsCjxxList = DbServices.getInstance(getBaseContext()).querySfzh(idCardData.getSfzh());
                if (bkKsCjxxList.size() > 0) {
                    new SelectDialog(CJActivity.this, R.style.dialog, "该考生已采集过特征，重复采集会覆盖上一次采集的数据，是否覆盖采集信息？", new SelectDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                DbServices.getInstance(getBaseContext()).deleteNote(bkKsCjxxList.get(0).getId());
                                if (bkKsCjxxList.get(0).getRl_picpath() != null) {
                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getRl_picpath());
                                }
                                if (bkKsCjxxList.get(0).getZw_picpath() != null) {
                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getZw_picpath());
                                }
                                if (bkKsCjxxList.get(0).getSfz_picpath() != null) {
                                    FileUtils.deleteFile(bkKsCjxxList.get(0).getSfz_picpath());
                                }
                                handler.removeCallbacks(runnable01); //停止刷新
                                getCjIdCard();
                                OnBnBegin();
                                dialog.dismiss();
                            } else {
                                idCardData = null;
                                if (idCardData == null) {
                                    handler.postDelayed(runnable01, 100);
                                }
                            }
                        }
                    }).setTitle("提示").show();
                } else {
                    handler.removeCallbacks(runnable01); //停止刷新
                    getCjIdCard();
                    OnBnBegin();
                }
            } else {
                handler.postDelayed(this, 100);// 间隔1秒
            }
        }
    };

    private Runnable runnable03 = new Runnable() {
        public void run() {
            initViewParams();
        }
    };

    /**
     * 指纹采集
     */
    private void getCjIdCard() {
        llIdcard.setVisibility(View.VISIBLE);
        tvIdMsg.setVisibility(View.GONE);
        ivIdFace.setImageBitmap(idCardData.getMap());
        tvName.setText(idCardData.getXm());
        tvSex.setText(idCardData.getXb());
        tvCardId.setText(idCardData.getSfzh());
        isCJ = true;
        include_idcard.setVisibility(View.GONE);
        include_finger.setVisibility(View.VISIBLE);
        tvTitle.setText("采集指纹");
    }

    private void getWIdCard() {
        idCardData = new IDCardData();
        idCardData.setSfzh(et_input);
        llIdcard.setVisibility(View.VISIBLE);
        tvIdMsg.setVisibility(View.GONE);
        ivIdFace.setImageResource(R.drawable.img_module_tab_collect_base_student);
        tvName.setText("无");
        tvSex.setText("无");
        tvCardId.setText(idCardData.getSfzh());
        isCJ = true;
        include_idcard.setVisibility(View.GONE);
        include_finger.setVisibility(View.VISIBLE);
        tvTitle.setText("采集指纹");
    }

    private void initViewParams() {
        llPreCamera.setVisibility(View.GONE);
        rlTakeCamera.setVisibility(View.VISIBLE);
        LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        surfaceView.setLayoutParams(params);
    }

    /**
     * 拍照
     */
    public void doTakePicture() {
        if (CameraInterface.getInstance().isPreviewing && (CameraInterface.getInstance().getCameraDevice() != null)) {
            CameraInterface.getInstance().getCameraDevice().takePicture(mShutterCallback, null, mJpegPictureCallback);
        }
    }

    /*为了实现拍照的快门声音及拍照保存照片需要下面三个回调变量*/
    Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
        //快门按下的回调，在这里我们可以设置类似播放“咔嚓”声之类的操作。默认的就是咔嚓。
        public void onShutter() {
            // TODO Auto-generated method stub
            LogUtil.i("myShutterCallback:onShutter...");
        }
    };
    Camera.PictureCallback mJpegPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            LogUtil.i("myJpegCallback:onPictureTaken...");
            Bitmap b = null;
            if (null != data) {
                b = BitmapFactory.decodeByteArray(data, 0, data.length);//data是字节数据，将其解析成位图
                int x = b.getWidth() / 4;
                int y = 0;
                int DST_RECT_WIDTH = b.getWidth() / 2;
                int DST_RECT_HEIGHT = b.getHeight();
                Bitmap bitmap = Bitmap.createBitmap(b, x, y, DST_RECT_WIDTH, DST_RECT_HEIGHT);
                CameraInterface.getInstance().rectBitmap = ThumbnailUtils.extractThumbnail(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()), 168, 240);
                tvPrePic.setVisibility(View.GONE);
                ivPrePic.setVisibility(View.VISIBLE);
                ivPrePic.setImageBitmap(CameraInterface.getInstance().rectBitmap);
                btnUsePic.setEnabled(true);
                CameraInterface.getInstance().mCamera.startPreview();
                CameraInterface.getInstance().isPreviewing = true;
            }
        }
    };

    /**
     * 点击返回处理
     */
    private void isFinish() {
        new SelectDialog(this, R.style.dialog, "确认终止本次采集吗？", new SelectDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    isCJ = false;
                    handler.removeCallbacks(runnable03);
                    bkKsCjxxList = DbServices.getInstance(getBaseContext()).loadAllNote();
                    tvCollectNum.setText("已采集：" + bkKsCjxxList.size());
                    dialog.dismiss();
                    idCardData = null;
                    fingerData = null;
                    tvPrePic.setVisibility(View.VISIBLE);
                    ivPrePic.setVisibility(View.GONE);
                    rlTakeCamera.setVisibility(View.GONE);
                    llPreCamera.setVisibility(View.VISIBLE);
                    btnUsePic.setEnabled(false);
                    include_face.setVisibility(View.GONE);
                    include_finger.setVisibility(View.GONE);
                    include_idcard.setVisibility(View.VISIBLE);
                    llIdcard.setVisibility(View.GONE);
                    tvIdMsg.setVisibility(View.VISIBLE);
                    rlFingerProcess.setVisibility(View.GONE);
                    tvFingerMsg.setVisibility(View.VISIBLE);
                    llFace.setVisibility(View.GONE);
                    tvFaceMsg.setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable01, 1000);
                    OnBnStop();
                }
            }
        }).setTitle("提示").show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isCJ) {
            isFinish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OnBnClose();
        handler.removeCallbacks(runnable01);
        handler.removeCallbacks(runnable03);
        handler = null;
        idCardData = null;
        fingerData = null;
        CameraInterface.getInstance().doStopCamera();
    }

    /**
     * 指纹采集
     *
     * @param captureMode     采集类型 0 表示只取指纹模板  1 表示取指纹模板和指纹图像
     * @param imageBuffer     存储返回图像数据的数组
     * @param imageAttributes 图像宽高数组
     * @param templateBuffer  存储返回指纹模板数据的数组
     */
    @Override
    public void captureOK(int captureMode, byte[] imageBuffer, int[] imageAttributes, final byte[] templateBuffer) {
        final int[] attributes = imageAttributes;
        final byte[] imgBuffer = imageBuffer;
        final byte[] tmpBuffer = templateBuffer;
        final int capMode = captureMode;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (capMode == FingerprintCaptureListener.MODE_CAPTURE_TEMPLATEANDIMAGE) {
                    soundPool.play(musicId.get(1), 1, 1, 0, 0, 1);
                    Bitmap mBitMap = ToolUtils.renderCroppedGreyScaleBitmap(imgBuffer, attributes[0], attributes[1]);
                    fingerData = new FingerData();
                    fingerData.setFingerImage(FileUtils.Bitmap2Bytes(mBitMap));
                    fingerData.setFingerFeatures(tmpBuffer);
                    fingerData.setQuality(0);
                    tvFingerMsg.setVisibility(View.GONE);
                    rlFingerProcess.setVisibility(View.VISIBLE);
                    ivLeftFinger.setVisibility(View.VISIBLE);
                    byte[] bytes = fingerData.getFingerImage();
                    zwBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length); //生成位图
                    ivRightFinger.setImageBitmap(zwBitmap); //显示图片
                    tv_quality.setText(Integer.toString(fingerData.getQuality()));
                    ivRightFinger.setVisibility(View.VISIBLE);
                    ivFingerOk.setVisibility(View.VISIBLE);
                    include_finger.setVisibility(View.GONE);
                    include_face.setVisibility(View.VISIBLE);
                    tvTitle.setText("采集人脸照片");
                    handler.postDelayed(runnable03, 500);
                    OnBnStop();
                }
            }
        });
    }

    @Override
    public void captureError(FingerprintSensorException e) {
        final FingerprintSensorException exp = e;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("captureError  errno=" + exp.getErrorCode() + ",内部错误代码: " + exp.getInternalErrorCode() + ",message=" + exp.getMessage());
            }
        });
    }
}

