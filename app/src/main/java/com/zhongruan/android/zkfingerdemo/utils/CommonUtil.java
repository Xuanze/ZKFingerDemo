package com.zhongruan.android.zkfingerdemo.utils;

import com.zhongruan.android.zkfingerdemo.idcardengine.IDCardData;
import com.zkteco.android.IDReader.IDPhotoHelper;
import com.zkteco.android.IDReader.WLTService;
import com.zkteco.android.biometric.module.idcard.meta.IDCardInfo;


public class CommonUtil {

    public static IDCardData getIDCardData(IDCardInfo card) {
        IDCardData data = new IDCardData();
        data.setXm(card.getName());
        data.setXb(card.getSex().toString());
        data.setMz(card.getNation().toString());
        data.setBirth(card.getBirth());
        data.setAddress(card.getAddress());
        data.setSfzh(card.getId());
        data.setQfjg(card.getDepart());
        data.setYxjsrq(card.getValidityTime());
        if (card.getPhotolength() > 0) {
            byte[] buf = new byte[WLTService.imgLength];
            if (1 == WLTService.wlt2Bmp(card.getPhoto(), buf)) {
                data.setMap(IDPhotoHelper.Bgr2Bitmap(buf));
            }
        }
        return data;
    }
}
