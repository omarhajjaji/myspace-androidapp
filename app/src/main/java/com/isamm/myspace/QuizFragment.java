package com.isamm.myspace;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class QuizFragment extends Fragment implements View.OnClickListener {

    private Button a_btn;
    private Button b_btn;
    private Button c_btn;
    private Button d_btn;

    private TextView questionTextView;

    private int score = 0;

    // to keep current question track
    private int currentQuestionIndex = 0;

    private ImageView questionImg;

    private ImageView firstCircle;
    private ImageView secondCircle;
    private ImageView thirdCircle;
    private ImageView fourthCircle;
    private ImageView fifthCircle;

    private ImageView [] checker;
    private int primaryColor;
    // array of objects of class Question
    // providing questions from string
    // resource and the correct ans
    private Question[] questionBank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionBank = new Question[]{

                new Question(R.string.question1niv1, "a",
                        new String[]{getString(R.string.jupiter),getString(R.string.mercury),getString(R.string.earth),getString(R.string.mars)}),
                new Question(R.string.question2niv1, "b",
                        new String[]{getString(R.string.pluto),getString(R.string.mercury),getString(R.string.venus),getString(R.string.jupiter)}),
                new Question(R.string.question3niv1, "c",
                        new String[]{getString(R.string.mars),getString(R.string.earth),getString(R.string.venus),getString(R.string.saturn)} ),
                new Question(R.string.question4niv1, "d",
                        new String[]{getString(R.string.earth),getString(R.string.mars),getString(R.string.pluto),getString(R.string.mercury)}),
                new Question(R.string.question5niv1, "a",
                        new String[]{getString(R.string.saturn),getString(R.string.mercury),getString(R.string.venus),getString(R.string.jupiter)}),
        };

        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        a_btn = view.findViewById(R.id.btn_a);
        b_btn = view.findViewById(R.id.btn_b);
        c_btn = view.findViewById(R.id.btn_c);
        d_btn = view.findViewById(R.id.btn_d);

        //Quiz progression checker (circles)
        firstCircle = view.findViewById(R.id.firstCircle);
        secondCircle = view.findViewById(R.id.secondCircle);
        thirdCircle = view.findViewById(R.id.thirdCircle);
        fourthCircle = view.findViewById(R.id.fourthCircle);
        fifthCircle = view.findViewById(R.id.fifthCircle);
        checker = new ImageView[] {firstCircle,secondCircle,thirdCircle,fourthCircle,fifthCircle};

        questionTextView = view.findViewById(R.id.question_text_view);
        questionImg = view.findViewById(R.id.question_img_view);

        a_btn.setOnClickListener(this);
        b_btn.setOnClickListener(this);
        c_btn.setOnClickListener(this);
        d_btn.setOnClickListener(this);

        updateQuestion();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_a:
                checkAnswer("a");
                break;

            case R.id.btn_b:
                checkAnswer("b");
                break;

            case R.id.btn_c:
                checkAnswer("c");
                break;

            case R.id.btn_d:
                checkAnswer("d");
                break;
        }

        if (currentQuestionIndex < 6) {
            // we are safe now!
            currentQuestionIndex = currentQuestionIndex + 1;
            // last question reached
            if (currentQuestionIndex == 5) {
                questionImg.setVisibility(View.INVISIBLE);
                //TODO: set image of winning vs losing
                questionTextView.setText(getString(R.string.score_display, score));
                //update score
                MainMenu.player.addScore(score);
                MainMenu.player.updateSharedPref(getContext());
                // making buttons
                // invisible
                a_btn.setVisibility(View.INVISIBLE);
                b_btn.setVisibility(View.INVISIBLE);
                c_btn.setVisibility(View.INVISIBLE);
                d_btn.setVisibility(View.INVISIBLE);
            } else {
                updateQuestion();
            }
        }
    }

    private void updateQuestion()
    {


        // setting the textview with new question
        questionTextView.setText(questionBank[currentQuestionIndex].getQuestionTextID());
        a_btn.setText(questionBank[currentQuestionIndex].getOptionByIndex(0));
        b_btn.setText(questionBank[currentQuestionIndex].getOptionByIndex(1));
        c_btn.setText(questionBank[currentQuestionIndex].getOptionByIndex(2));
        d_btn.setText(questionBank[currentQuestionIndex].getOptionByIndex(3));
        // setting up image for each
        // question
        switch (currentQuestionIndex) {
            case 0:
                questionImg.setImageResource(R.drawable.jupiterq);
                break;
            case 1:
                questionImg.setImageResource(R.drawable.mercuryq);
                break;
            case 2:
                questionImg.setImageResource(R.drawable.venusq);
                break;
            case 3:
                questionImg.setImageResource(R.drawable.mercuryq);
                break;
            case 4:
                questionImg.setImageResource(R.drawable.saturnq);
                break;

        }
    }

    private void checkAnswer(String userAnswer)
    {
        int toastMessageId;
        // getting correct ans of current question

        // if ans matches with the
        // button clicked

        if (questionBank[currentQuestionIndex].isCorrectAnswer(userAnswer)) {
            toastMessageId = R.string.correct_answer;
            checker[currentQuestionIndex].setColorFilter(Color.argb(255, 42, 125, 0));
            score++;
        }
        else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer;
            checker[currentQuestionIndex].setColorFilter(Color.argb(255, 199, 0, 0));
        }

        Toast.makeText(getContext(), toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();

    }



}