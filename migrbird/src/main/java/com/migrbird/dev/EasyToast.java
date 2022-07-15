package com.migrbird.dev;

import android.content.Context;
import android.widget.Toast;

public class EasyToast {

    public static void showToast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
