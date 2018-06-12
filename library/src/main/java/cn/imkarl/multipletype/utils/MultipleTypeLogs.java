package cn.imkarl.multipletype.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import cn.imkarl.multipletype.BuildConfig;

/**
 * 日志根据
 * @author imkarl
 */
public class MultipleTypeLogs {
    public static boolean DEBUG = BuildConfig.DEBUG;

    private MultipleTypeLogs() {
    }

    public static void w(@NonNull String message) {
        if (DEBUG) {
            Log.w("MultipleTypeAdapter", message);
        }
    }

}
