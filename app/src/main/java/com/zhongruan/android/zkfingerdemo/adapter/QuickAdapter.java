package com.zhongruan.android.zkfingerdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhongruan.android.zkfingerdemo.R;
import com.zhongruan.android.zkfingerdemo.db.entity.Bk_ks_cjxx;
import com.zhongruan.android.zkfingerdemo.utils.FileUtils;

import java.util.List;


/**
 * Created by castl on 2016/5/20.
 */
public class QuickAdapter extends BaseQuickAdapter<Bk_ks_cjxx> {
    public QuickAdapter(Context context, List<Bk_ks_cjxx> data) {
        super(context, R.layout.pad_adapter_cjxx_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Bk_ks_cjxx bk_ks_cjxx) {
        if (bk_ks_cjxx.getIsSbzt() == 0) {
            String s = FileUtils.getAppSavePath() + "/" + bk_ks_cjxx.getRl_picpath();
            Bitmap bitmap = BitmapFactory.decodeFile(s);
            baseViewHolder.setText(R.id.tv_student_name, bk_ks_cjxx.getSfz_xm())
                    .setImageBitmap(R.id.iv_student_pic, bitmap)
                    .setBackgroundRes(R.id.iv_upload_state, R.drawable.img_module_tab_collect_dynamiclist_not_upload)
                    .setText(R.id.tv_upload_state, "未上报")
                    .setBackgroundColor(R.id.v_name_line, this.mContext.getResources().getColor(R.color.cjtj_gray))
                    .setText(R.id.tv_student_sfzh, bk_ks_cjxx.getSfz_sfzh());
        } else {
            String s = FileUtils.getAppSavePath() + "/" + bk_ks_cjxx.getRl_picpath();
            Bitmap bitmap = BitmapFactory.decodeFile(s);
            baseViewHolder.setText(R.id.tv_student_name, bk_ks_cjxx.getSfz_xm())
                    .setImageBitmap(R.id.iv_student_pic, bitmap)
                    .setBackgroundRes(R.id.iv_upload_state, R.drawable.img_module_tab_collect_dynamiclist_upload_finished)
                    .setText(R.id.tv_upload_state, "已上报")
                    .setBackgroundColor(R.id.v_name_line, this.mContext.getResources().getColor(R.color.cjtj_green))
                    .setText(R.id.tv_student_sfzh, bk_ks_cjxx.getSfz_sfzh());
        }
    }
}