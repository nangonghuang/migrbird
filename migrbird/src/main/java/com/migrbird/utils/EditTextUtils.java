package com.migrbird.utils;

import android.text.InputFilter;
import android.widget.EditText;

public class EditTextUtils {

    public static void addFilters(EditText editText, InputFilter filter) {
        final InputFilter[] filters = editText.getFilters();
        final InputFilter[] newFilters = new InputFilter[filters.length + 1];
        System.arraycopy(filters, 0, newFilters, 0, filters.length);
        newFilters[filters.length] = filter;
        editText.setFilters(newFilters);
    }
}
