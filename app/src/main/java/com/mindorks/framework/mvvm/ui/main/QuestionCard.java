/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mindorks.framework.mvvm.ui.main;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.mindorks.framework.mvvm.R;
import com.mindorks.framework.mvvm.data.model.db.Option;
import com.mindorks.framework.mvvm.data.model.others.QuestionCardData;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by amitshekhar on 08/07/17.
 */

@NonReusable
@Layout(R.layout.card_layout)
public class QuestionCard {

    private static final String TAG = "QuestionCard1";
    MainViewModel mainViewModel;

    int attempt = 0;
    int wrong = 0;
    int correct = 0;
    String value = "";


    @View(R.id.btn_option_1)
    private Button mOption1Button;

    @View(R.id.btn_option_2)
    private Button mOption2Button;

    @View(R.id.btn_option_3)
    private Button mOption3Button;

    @View(R.id.btn_option_4)
    private Button mOption4Button;

    @View(R.id.iv_pic)
    private ANImageView mPicImageView;

    private QuestionCardData mQuestionCardData;

    @View(R.id.tv_question_txt)
    private TextView mQuestionTextView;

    public QuestionCard(QuestionCardData questionCardData) {
        mQuestionCardData = questionCardData;
    }

    @Click(R.id.btn_option_1)
    public void onOption1Click() {

        value = mOption1Button.getText().toString();
        showCorrectOptions(value);


    }

    @Click(R.id.btn_option_2)
    public void onOption2Click() {
        value = mOption2Button.getText().toString();
        showCorrectOptions(value);

    }

    @Click(R.id.btn_option_3)
    public void onOption3Click() {
        value = mOption3Button.getText().toString();
        showCorrectOptions(value);

    }

    @Click(R.id.btn_option_4)
    public void onOption4Click() {
        value = mOption4Button.getText().toString();
        showCorrectOptions(value);

    }


    @Resolve
    private void onResolved() {
        mQuestionTextView.setText(mQuestionCardData.question.questionText);
        if (mQuestionCardData.mShowCorrectOptions) {
            showCorrectOptions(value);
        }

        for (int i = 0; i < 4; i++) {

            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;
                    break;
                case 1:
                    button = mOption2Button;
                    break;
                case 2:
                    button = mOption3Button;
                    break;
                case 3:
                    button = mOption4Button;
                    break;
            }


            if (mQuestionCardData.options.size() == 4) {
                mOption4Button.setVisibility(android.view.View.VISIBLE);
            } else {
                mOption4Button.setVisibility(android.view.View.GONE);
            }


            if (button != null) {

                button.setText(mQuestionCardData.options.get(i).optionText);

            }

            if (mQuestionCardData.question.imgUrl != null) {
                mPicImageView.setImageUrl(mQuestionCardData.question.imgUrl);
            }
        }
    }


    private void showCorrectOptions(String value) {
        mQuestionCardData.mShowCorrectOptions = true;


        for (int i = 0; i < 4; i++) {
            Option option = mQuestionCardData.options.get(i);
            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;

                    break;
                case 1:
                    button = mOption2Button;


                    break;
                case 2:
                    button = mOption3Button;

                    break;
                case 3:
                    button = mOption4Button;

                    break;
            }

            if (button != null) {


                if (value.equalsIgnoreCase(option.optionText)) {
                    if (option.isCorrect) {
                        button.setBackgroundColor(Color.GREEN);
                    } else {
                        button.setBackgroundColor(Color.RED);

                    }
                }


            }


        }

    }


}
