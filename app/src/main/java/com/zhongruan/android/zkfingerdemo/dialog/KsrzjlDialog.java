package com.zhongruan.android.zkfingerdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.adapter.KSRZXQHistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.RZDJJLHistoryAdapter;
import com.zhongruan.android.zkfingerdemo.adapter.view.KsRzGjHistory;
import com.zhongruan.android.zkfingerdemo.adapter.view.KsRzjlView;
import com.zhongruan.android.zkfingerdemo.camera.util.DisplayUtil;
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
        this.mContext = context;
        this.ksxm = ksxm;
        this.kcno = kcno;
        this.ksno = ksno;
        this.kmno = kmno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pad_dialog_rzgj_statistic, null);
        setContentView(view);
        ViewGroup.LayoutParams localLayoutParams = view.getLayoutParams();
        localLayoutParams.height = (4 * (DisplayUtil.getScreenMetrics(mContext).y / 5));
        localLayoutParams.width = (4 * (DisplayUtil.getScreenMetrics(mContext).x / 5));
        view.setLayoutParams(localLayoutParams);
        initView();
    }

    private void initView() {
        mDialogRzgjKsxm = findViewById(R.id.dialog_rzgj_ksxm);
        mDialogRzgjKsno = findViewById(R.id.dialog_rzgj_ksno);
        mExpandLvRzgj = findViewById(R.id.expand_lv_rzgj);
        mDialogRzgjKsxm.setText(ksxm);
        mDialogRzgjKsno.setText(ksno);
        List<Sfrz_rzjg> rzjgList = DbServices.getInstance(mContext).selectKSrzjg(ksno, kmno, kcno);
        Log.i("RZXQ", "kmno: " + kmno);
        Log.i("RZXQ", "kcno: " + kcno);
        historyList = new ArrayList<>();
        for (int i = 0; i < rzjgList.size(); i++) {
            KsRzGjHistory history = new KsRzGjHistory();
            history.setKs_rzzt(rzjgList.get(i).getRzjg_ztid());
            history.setKs_rztime(rzjgList.get(i).getRzjg_time());
            Log.i("RZXQ", "rzjgList: " + rzjgList.get(i).getRzjg_ztid());
            Log.i("RZXQ", "rzjgList: " + rzjgList.get(i).getRzjg_time());
            List<Sfrz_rzjl> rzjls = DbServices.getInstance(mContext).selectKSrzjls(rzjgList.get(i).getRzjgid());
            List<KsRzjlView> list = new ArrayList<>();
            for (int j = 0; j < rzjls.size(); j++) {
                KsRzjlView view = new KsRzjlView();
                view.setKs_rzxppith(rzjls.get(j).getRzjl_pith());
                view.setKs_rzfs(rzjls.get(j).getRzjl_rzfsno());
                view.setKs_rztime(rzjls.get(j).getRzjl_time());

                Log.i("RZXQ", "rzjgList: " + rzjls.get(j).getRzjl_rzfsno());
                list.add(view);
            }
            history.setViewList(list);
            historyList.add(history);
        }
        ksrzxqHistoryAdapter = new KSRZXQHistoryAdapter(mContext, historyList);
        mExpandLvRzgj.setAdapter(ksrzxqHistoryAdapter);

    }
}