package com.migrbird.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/**
 * 只能输入中文、英文、数字和下划线，不符合的会被删除
 */
public class EnChNumberInputFilter extends InputFilter.LengthFilter {

    public EnChNumberInputFilter(int max) {
        super(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        CharSequence charSequence = super.filter(source, start, end, dest, dstart, dend);
        String regex = "^[\\u4E00-\\u9FA5a-z0-9A-Z_]+$";
        boolean matches = Pattern.matches(regex, source.toString());
        if (matches) {
            return charSequence;
        } else {
            return "";
        }
    }
}
