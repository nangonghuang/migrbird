package com.migrbird.dev;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.migrbird.utils.Utils;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 对话框基础类，构造的时候设置对话框的布局文件binding作为范型参数,主要处理dialog大小的问题。
 * 可以指定大小，也可以直接使用xml的设置。
 *
 * @param <T>
 */
public abstract class BaseBindingDialog<T extends ViewBinding> extends Dialog {

    protected T binding;
    private int initWidth;
    private int intHeight;
    private float dimAmount = 0.1f;

    /**
     * 直接指定dialog的大小
     *
     * @param context
     * @param widthInDp
     * @param heightInDp
     */
    public BaseBindingDialog(@NonNull Context context, int widthInDp, int heightInDp) {
        super(context);

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.initWidth = widthInDp;
        this.intHeight = heightInDp;
    }

    /**
     * 根据根布局自适应大小，需要在外面包裹一层FrameLayout。 因为dialog在setContentView的时候会把我们自己定义的根布局强制设置成match_parent
     *
     * @param context
     */
    public BaseBindingDialog(@NonNull Context context) {
        super(context);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
            Class<T> clazz = (Class<T>) actualTypeArgument;
            Method method = clazz.getMethod("inflate", LayoutInflater.class);
            binding = (T) method.invoke(null, getLayoutInflater());
            View view = binding.getRoot();
            setContentView(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (initWidth > 0) {
            lp.width = Utils.dp2px(initWidth, getContext().getResources().getDisplayMetrics());
        } else if (initWidth < 0) {
            // LayoutParams.MATCH_PARENT || WRAP_CONTENT
            lp.width = initWidth;
        }
        if (intHeight > 0) {
            lp.height = Utils.dp2px(intHeight, getContext().getResources().getDisplayMetrics());
        } else if (intHeight < 0) {
            //  LayoutParams.MATCH_PARENT || WRAP_CONTENT
            lp.height = intHeight;
        }
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable());

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.show();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_FULLSCREEN;
        binding.getRoot().setSystemUiVisibility(uiOptions);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
}
