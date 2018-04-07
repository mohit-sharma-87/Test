package com.codelife.test.mvp.Contract

import com.codelife.test.models.QuestionInfo
import com.codelife.test.mvp.IPresenter
import com.codelife.test.mvp.IView

interface QuestionListContract {

    interface Presenter : IPresenter<View> {
        fun getQuestionList()
    }

    interface View : IView {
        fun onQuestionListSucess(data: ArrayList<QuestionInfo>)
    }


}