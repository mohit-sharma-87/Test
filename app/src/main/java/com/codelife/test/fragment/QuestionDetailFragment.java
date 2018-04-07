package com.codelife.test.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codelife.test.R;
import com.codelife.test.adapter.OptionListAdapter;
import com.codelife.test.common.ItemClickSupport;
import com.codelife.test.databinding.LayoutQuestionDetailBinding;
import com.codelife.test.models.QuestionInfo;
import com.codelife.test.mvp.IPresenter;
import com.codelife.test.mvp.IView;
import com.codelife.test.utils.SpacesItemDecoration;
import com.google.gson.Gson;

/**
 * Created by mohitsharma on 06/04/18.
 */

public class QuestionDetailFragment extends BaseFragment {

    public static final String DATA_KEY = "data";
    public static final String POSITION_KEY = "position";
    private LayoutQuestionDetailBinding binding;
    private QuestionInfo info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_question_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String json = getArguments().getString(DATA_KEY);
        info = new Gson().fromJson(json, QuestionInfo.class);
        intiView(info);
        binding.fabQuestionDetailSave.setOnClickListener(v -> {
            Intent intent = getActivity().getIntent();
            intent.putExtra(DATA_KEY,new Gson().toJson(info));
            intent.putExtra(POSITION_KEY,getArguments().getInt(POSITION_KEY));
            getTargetFragment().onActivityResult(getTargetRequestCode(), 1, intent);
            getFragmentManager().popBackStack();
        });
    }


    private void intiView(QuestionInfo info) {
        binding.tvQuestionDetailTitle.setText(info.getQuestionTitle());
        binding.rvAnswerOptions.setItemAnimator(new DefaultItemAnimator());
        binding.rvAnswerOptions.addItemDecoration(new SpacesItemDecoration(1, 10, true));
        binding.rvAnswerOptions.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        OptionListAdapter adapter = new OptionListAdapter(info.getAnswers());
        binding.rvAnswerOptions.setAdapter(adapter);

        ItemClickSupport.addTo(binding.rvAnswerOptions)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    info.setSelectedOption(info.getAnswers().get(position));
                    ((OptionListAdapter) recyclerView.getAdapter()).updateSelectedItemUI(v, position);
                });
    }


    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected IView getPresenterView() {
        return null;
    }
}
