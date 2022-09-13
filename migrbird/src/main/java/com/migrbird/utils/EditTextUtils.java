package com.migrbird.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import java.util.regex.Pattern;

public class EditTextUtils {

    public static void addFilters(EditText editText, InputFilter filter) {
        final InputFilter[] filters = editText.getFilters();
        final InputFilter[] newFilters = new InputFilter[filters.length + 1];
        System.arraycopy(filters, 0, newFilters, 0, filters.length);
        newFilters[filters.length] = filter;
        editText.setFilters(newFilters);
    }

    public static class EnChNumberLengthFilter extends InputFilter.LengthFilter {

        public EnChNumberLengthFilter(int max) {
            super(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            CharSequence charSequence = super.filter(source, start, end, dest, dstart, dend);
            boolean matches = RegexUtils.isMatch(RegexUtils.REGEX_USERNAME, charSequence);
            if (matches) {
                return charSequence;
            } else {
                return "";
            }
        }
    }
}
