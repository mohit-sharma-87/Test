package com.codelife.test.mvp.presenter

import com.codelife.test.feature.Feature
import com.codelife.test.mvp.Contract.QuestionListContract
import org.greenrobot.eventbus.EventBus

/**
 * Created by mohitsharma on 05/04/18.
 */


class QuestionListPresenter(private val view: QuestionListContract.View, private val eventBus: EventBus, private val feature: Feature) : QuestionListContract.Presenter {


    override fun start(view: QuestionListContract.View?) {
        // TODO : Left on purpose Useful in bigger application
    }

    override fun stop() {
        // TODO : Left on purpose Useful in bigger application
    }

    override fun getQuestionList() {
        val questionList = feature.getQuestionList()
        view.onQuestionListSucess(questionList.data)
    }

}


