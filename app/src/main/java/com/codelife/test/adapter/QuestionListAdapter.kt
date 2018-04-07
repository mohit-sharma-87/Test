package com.codelife.test.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codelife.test.databinding.ItemQuestionViewBinding
import com.codelife.test.models.QuestionInfo

class QuestionListAdapter(val questionList : ArrayList<QuestionInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(ItemQuestionViewBinding.inflate(inflater))
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (((holder as ListViewHolder).binding) as ItemQuestionViewBinding).questionInfo = questionList[position]
    }

}