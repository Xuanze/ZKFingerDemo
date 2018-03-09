package com.zhongruan.android.zkfingerdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.FileBrowserAdapter;
import com.zhongruan.android.zkfingerdemo.utils.LogUtil;

public class ZipListDialog extends Dialog implements View.OnClickListener {

    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private FileBrowserAdapter fileBrowserAdapter;
    private TextView dialogZipTitle;
    private Button noZipButton;
    private Button yesZipButton;
    private ListView listView;
    public static int position = 0;


    public ZipListDialog(Context context, int themeResId, ListView content, OnCloseListener listener) {
        super(context, themeResId);
        this.listView = content;
        this.listener = listener;
    }

    public ZipListDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ZipListDialog setAdapter(FileBrowserAdapter fileBrowserAdapter) {
        this.fileBrowserAdapter = fileBrowserAdapter;
        return this;
    }

    public ZipListDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public ZipListDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_zip_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {

        dialogZipTitle = findViewById(R.id.dialog_zip_title);
        noZipButton = findViewById(R.id.no_zip_Button);
        yesZipButton = findViewById(R.id.yes_zip_Button);
        listView = findViewById(R.id.lv_zip_Content);
        noZipButton.setOnClickListener(this);
        yesZipButton.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fileBrowserAdapter.setSelectedPosition(i);
                fileBrowserAdapter.notifyDataSetChanged();
                position = i;
                LogUtil.i(i);
            }
        });

        if (!TextUtils.isEmpty(positiveName)) {
            yesZipButton.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            noZipButton.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            dialogZipTitle.setText(title);
        }

        if (fileBrowserAdapter != null) {
            listView.setAdapter(fileBrowserAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_zip_Button:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.yes_zip_Button:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}