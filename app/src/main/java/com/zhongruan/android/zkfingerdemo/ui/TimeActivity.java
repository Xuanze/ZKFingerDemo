package com.zhongruan.android.zkfingerdemo.ui;

import android.app.AlarmManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.base.BaseActivity;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Administrator on 2017/8/1.
 */

public class TimeActivity extends BaseActivity implements OnClickListener {
    private TextView calibrationNowtime, calibrationNettime;
    private LinearLayout calibrationConfirm;
    private DatePicker date;
    private TimePicker time;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_time);
    }

    @Override
    public void initViews() {
        calibrationNowtime = findViewById(R.id.calibration_nowtime);
        calibrationNettime = findViewById(R.id.calibration_nettime);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        calibrationConfirm = findViewById(R.id.calibration_confirm);
        this.calibrationNowtime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void initListeners() {
        calibrationConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calibration_confirm:
                doConfirmTime();
                break;
        }
    }

    public void doConfirmTime() {
        String date = this.date.getYear() + "-" + (this.date.getMonth() + 1) + "-" + this.date.getDayOfMonth() + " " + this.time.getCurrentHour() + ":" + this.time.getCurrentMinute() + ":" + "00";
        timeSynchronization(date);
        finish();
    }

    public boolean timeSynchronization(String time) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).setTime(c.getTimeInMillis());
            return true;
        } catch (Exception e) {
            LogUtil.i("出错了");
            e.printStackTrace();
            return false;
        }
    }
}
