package com.codelife.test.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionInfo(

        @SerializedName("questionTitle") val questionTitle: String,
        @SerializedName("option") val answers: List<AnswerInfo>,
        @SerializedName("answer") val solution: String) {

    @Expose
    var selectedOption: AnswerInfo? = null


}