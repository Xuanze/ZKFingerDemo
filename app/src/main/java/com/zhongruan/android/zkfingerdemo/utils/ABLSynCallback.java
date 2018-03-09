package com.zhongruan.android.zkfingerdemo.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ABLSynCallback {
    static class AnonymousClass2 extends Handler {
        final ForegroundCall val$forCall;

        AnonymousClass2(Looper x0, ForegroundCall foregroundCall) {
            this.val$forCall = foregroundCall;
        }

        public void handleMessage(Message msg) {
            if (this.val$forCall != null) {
                this.val$forCall.callback(msg.obj);
            }
        }
    }

    static class AnonymousClass3 extends Thread {
        final BackgroundCall val$bgCall;
        final Handler val$handler;

        AnonymousClass3(BackgroundCall backgroundCall, Handler handler) {
            this.val$bgCall = backgroundCall;
            this.val$handler = handler;
        }

        public void run() {
            Message message = new Message();
            try {
                if (this.val$bgCall != null) {
                    message.obj = this.val$bgCall.callback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.val$handler.sendMessage(message);
        }
    }

    public interface BackgroundCall {
        Object callback();
    }

    public interface ForegroundCall {
        void callback(Object obj);
    }

    public static void call(BackgroundCall bgCall, ForegroundCall forCall) {
        new AnonymousClass3(bgCall, new AnonymousClass2(Looper.getMainLooper(), forCall)).start();
    }
}



