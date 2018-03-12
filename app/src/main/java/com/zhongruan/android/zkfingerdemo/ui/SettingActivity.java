package com.zhongruan.android.zkfingerdemo.ui;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;

/**
 * Created by LHJ on 2018/3/9.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLlDataBack;

    private Button btnPlay, btnUpper, btnLower;
    private ToggleButton tbMute;
    private MediaPlayer mediaPlayer; //声频
    private AudioManager audioManager; //音频

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {
        mLlDataBack = findViewById(R.id.ll_data_back);
        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        btnPlay = findViewById(R.id.btnPlay);
        btnUpper = findViewById(R.id.btnUpper);
        btnLower = findViewById(R.id.btnLower);
        btnPlay.setOnClickListener(this);
        btnUpper.setOnClickListener(this);
        btnLower.setOnClickListener(this);
        tbMute = findViewById(R.id.tbMute);
        tbMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked); //设置是否静音
            }
        });

    }

    @Override
    public void initListeners() {
        mLlDataBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_data_back:
                finish();
                break;
            case R.id.btnPlay://播放音乐
                mediaPlayer = MediaPlayer.create(SettingActivity.this, R.raw.beep);
                mediaPlayer.setLooping(true);//设置循环播放
                mediaPlayer.start();//播放声音
                break;
            case R.id.btnUpper://增多音量
                //adjustStreamVolume: 调整指定声音类型的音量
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);  //调高声音
                break;
            case R.id.btnLower://减少音量
                //第一个参数：声音类型
                //第二个参数：调整音量的方向
                //第三个参数：可选的标志位
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);//调低声音
                break;
        }
    }
}
