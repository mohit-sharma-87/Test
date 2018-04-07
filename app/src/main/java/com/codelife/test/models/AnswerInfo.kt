package com.codelife.test.models

import com.google.gson.annotations.SerializedName

data class AnswerInfo(@SerializedName("id") val id: String,
                      @SerializedName("displayText") val displayText: String)