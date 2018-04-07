package com.codelife.test.feature


import com.codelife.test.dataService.DataService
import com.codelife.test.models.QuestionModelResponse
import com.google.gson.Gson

/**
 * Created by mohitsharma on 09/10/17.
 */

class Feature(private val dataService: DataService) {

    fun getQuestionList() : QuestionModelResponse {
        val jsonString: String = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"questionTitle\": \"Which of the following is not a county?\",\n" +
                "      \"option\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"displayText\": \"India\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"displayText\": \"Bhutan\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"displayText\": \"Punjab\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"answer\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"questionTitle\": \"Who are you ?\",\n" +
                "      \"option\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"displayText\": \"Me\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"displayText\": \"You\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"displayText\": \"Don't know\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"answer\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"questionTitle\": \"Lorem ipsum dolor sit amet, pro quodsi ornatus necessitatibus te, posidonium comprehensam per no.\",\n" +
                "      \"option\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"displayText\": \"Duo agam aperiam\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"displayText\": \"nec ea utamur\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"displayText\": \"imperdiet maiestatis\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"answer\": 3\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        return Gson().fromJson(jsonString, QuestionModelResponse::class.java)
    }

}
