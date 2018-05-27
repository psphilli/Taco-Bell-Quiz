package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Question Six - How old was Gidget when she died?      15yrs
 */

public class QuestionSix extends Fragment implements View.OnClickListener {
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;
    private TextView mAge;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static QuestionSix newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        QuestionSix fragment = new QuestionSix();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_six, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        mAge = view.findViewById(R.id.age_text_view);
        mAge.setText(Integer.toString(mAnswers.getSix()));

        Button mIncrement = view.findViewById(R.id.increment_age);
        Button mDecrement = view.findViewById(R.id.decrement_age);

        mIncrement.setOnClickListener(this);
        mDecrement.setOnClickListener(this);

        return view;
    }

    /**
     * Handler for both yes and no RadioButtons in the Fragment
     *
     * @param view The CheckBox which generated the event
     */
    @Override
    public void onClick(View view) {
        // Which, add or subtract
        int age = Integer.parseInt(mAge.getText().toString());
        if (view.getId() == R.id.decrement_age) {
            age--;
        }
        else {
            age++;
        }

        // Update UI
        mAge.setText(Integer.toString(age));

        // Save choice into the global quiz answers object
        mAnswers.setSix(age);
    }
}
