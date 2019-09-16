package com.example.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class HintAdapter extends ArrayAdapter<Object> {
    public HintAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
