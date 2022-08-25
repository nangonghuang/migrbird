package com.migrbird.binding;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseBindingActivity<T extends ViewBinding> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    protected T binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
            Class<T> clazz = (Class<T>) actualTypeArgument;
            Method method = clazz.getMethod("inflate", LayoutInflater.class);
            binding = (T) method.invoke(null, getLayoutInflater());

            setContentView(binding.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
