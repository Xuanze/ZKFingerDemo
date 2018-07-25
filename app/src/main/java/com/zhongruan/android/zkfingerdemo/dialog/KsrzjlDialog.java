package com.zhongruan.android.zkfingerdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.KSRZXQHistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.view.KsRzGjHistory;
import com.zhongruan.android.zkfingerdemo.adapter.view.KsRzjlView;
import com.zhongruan.android.zkfingerdemo.db.DbServices;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjg;
import com.zhongruan.android.zkfingerdemo.db.entity.Sfrz_rzjl;

import java.util.ArrayList;
import java.util.List;

public class KsrzjlDialog extends Dialog {
    private Context mContext;
    private TextView mDialogRzgjKsxm;
    private TextView mDialogRzgjKsno;
    private ExpandableListView mExpandLvRzgj;
    private String ksxm, kcno, ksno, kmno;
    private KSRZXQHistoryAdapter ksrzxqHistoryAdapter;
    private List<KsRzGjHistory> historyList;

    public KsrzjlDialog(@NonNull Context context, int themeResId, String ksxm, String kcno, String ksno, String kmno) {
        super(context, themeResId);
        this.ksxm = ksxm;
        this.kcno = kcno;
        this.ksno = ksno;
        this.kmno = kmno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pad_dialog_rzgj_statistic);
        initView();
    }

    private void initView() {
        mDialogRzgjKsxm = findViewById(R.id.dialog_rzgj_ksxm);
        mDialogRzgjKsno = findViewById(R.id.dialog_rzgj_ksno);
        mExpandLvRzgj = findViewById(R.id.expand_lv_rzgj);
        mDialogRzgjKsxm.setText(ksxm);
        mDialogRzgjKsno.setText(ksno);
        List<Sfrz_rzjg> rzjgList = DbServices.getInstance(mContext).selectKSrzjg(ksno, kmno, kcno);
        List<Sfrz_rzjl> rzjlList = DbServices.getInstance(mContext).selectKSrzjl(ksno, kmno, kcno);
        historyList = new ArrayList<>();
        for (int i = 0; i < rzjgList.size(); i++) {
            KsRzGjHistory history = new KsRzGjHistory();
            history.setKs_rzzt(rzjgList.get(i).getRzjg_ztid());
            history.setKs_rztime(rzjgList.get(i).getRzjg_time());
            List<KsRzjlView> list = new ArrayList<>();
            for (int j = 0; j < rzjlList.size(); j++) {
                KsRzjlView view = new KsRzjlView();
                view.setKs_rzxppith(rzjlList.get(j).getRzjl_pith());
                view.setKs_rzfs(rzjlList.get(j).getRzjl_rzfsno());
                view.setKs_rztime(rzjlList.get(j).getRzjl_time());
                list.add(view);
            }
            history.setViewList(list);
            historyList.add(history);
        }
        ksrzxqHistoryAdapter = new KSRZXQHistoryAdapter(mContext, historyList);
        mExpandLvRzgj.setAdapter(ksrzxqHistoryAdapter);
    }
}