package com.codelife.test.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by mohitsharma on 27/08/17.
 */

public class ListViewHolder extends ViewHolder {

    private ViewDataBinding binding;

    public ListViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
