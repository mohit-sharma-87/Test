package com.codelife.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codelife.test.databinding.ItemAnswerOptionViewBinding;
import com.codelife.test.models.AnswerInfo;

import java.util.List;

/**
 * Created by mohitsharma on 06/04/18.
 */

public class OptionListAdapter extends RecyclerView.Adapter {

    private List<AnswerInfo> infos;
    private View previousSelectedView;
    private int highlightedItemPosition = -1;


    public OptionListAdapter(List<AnswerInfo> infos) {
        this.infos = infos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAnswerOptionViewBinding binding = ItemAnswerOptionViewBinding.inflate(inflater, parent, false);
        return new ListViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AnswerInfo answerInfo = infos.get(position);
        ((ItemAnswerOptionViewBinding) ((ListViewHolder) holder).getBinding()).tvAnswerOption.setText(answerInfo.getDisplayText());
    }




    @Override
    public int getItemCount() {
        return infos.size();
    }

    public int getHighlightedItemPosition() {
        return highlightedItemPosition;
    }

    public void updateSelectedItemUI(View currentSelectedView, int position) {
        highlightedItemPosition = position;
        if (this.previousSelectedView != null)
            this.previousSelectedView.setAlpha(1f);

        if (position == -2) {
            previousSelectedView.setAlpha(1f);
        } else {
            this.previousSelectedView = currentSelectedView;
            currentSelectedView.setAlpha(0.5f);
        }
    }


}
