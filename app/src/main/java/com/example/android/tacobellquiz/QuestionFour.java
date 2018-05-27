package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

/**
 * Question Four - Is Taco Bell good to eat during pregnancy?     yes
 */

public class QuestionFour extends Fragment implements View.OnClickListener {
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static QuestionFour newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        QuestionFour fragment = new QuestionFour();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_four, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        RadioButton mYes = view.findViewById(R.id.yes_when_pregnant);
        RadioButton mNo = view.findViewById(R.id.no_when_pregnant);

        // Using class Boolean so that null will represent unset state
        if (mAnswers.getFour() != null) {
            if (mAnswers.getFour()) {
                mYes.setChecked(true);
            } else {
                mNo.setChecked(true);
            }
        }
        mYes.setOnClickListener(this);
        mNo.setOnClickListener(this);

        return view;
    }

    /**
     * Handler for both yes and no RadioButtons in the Fragment
     *
     * @param view The CheckBox which generated the event
     */
    @Override
    public void onClick(View view) {
        // Save choice into the global quiz answers object
        mAnswers.setFour(view.getId() == R.id.yes_when_pregnant);
    }
}
