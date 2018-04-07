package com.codelife.test.models

import com.google.gson.annotations.SerializedName

data class QuestionModelResponse(@SerializedName("data") var data : ArrayList<QuestionInfo>)


