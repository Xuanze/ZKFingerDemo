package com.zhongruan.android.zkfingerdemo.utils;

import android.util.Base64;
import android.util.Log;

import net.lingala.zip4j.util.InternalZipConstants;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import rx.android.BuildConfig;

public class CryptoTools {
    private byte[] DESIV;
    private byte[] DESkey;
    private AlgorithmParameterSpec iv;
    private Key mKey;

    public CryptoTools() {
        this.DESIV = new byte[]{(byte) 18, (byte) 52, (byte) 86, (byte) 120, (byte) -112, (byte) -85, (byte) -51, (byte) -17};
        this.iv = null;
        try {
            this.DESkey = "mstchina".getBytes("UTF-8");
            DESKeySpec keySpec = new DESKeySpec(this.DESkey);
            this.iv = new IvParameterSpec(this.DESIV);
            this.mKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
        } catch (Exception e) {
        }
    }

    public String getEncString(String inputString) {
        String outputString = BuildConfig.VERSION_NAME;
        try {
            String outputString2 = new String(Base64.encode(getEncCode(inputString.getBytes(InternalZipConstants.CHARSET_UTF8)), 0));
            return outputString2;
        } catch (Exception e) {
            Log.e("SSEC_RTX", "根据密钥加密字符串出错: ", e);
            return outputString;
        } finally {
        }
    }

    public String getDecString(String inputString) {
        String strMing = BuildConfig.VERSION_NAME;
        try {
            String strMing2 = new String(getDesCode(Base64.decode(inputString.getBytes(), 0)), InternalZipConstants.CHARSET_UTF8);
            return strMing2;
        } catch (Exception e) {
            Log.e("SSEC_RTX", "根据密钥解密字符串出错: ", e);
            return strMing;
        } finally {
        }
    }

    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, this.mKey, this.iv);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            Log.e("RTX", "根据密钥加密字节码出错: ", e);
        } finally {
        }
        return byteFina;
    }

    private byte[] getDesCode(byte[] byteD) {
        byte[] byteFina = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, this.mKey, this.iv);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            Log.e("RTX", "根据密钥解密字节码出错: ", e);
        } finally {
        }
        return byteFina;
    }
}
