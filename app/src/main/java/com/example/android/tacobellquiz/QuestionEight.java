package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Question Eight - In what year was the Cheese Quesadilla added to the menu?    2002
 */
public class QuestionEight extends Fragment implements View.OnClickListener {
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;
    private TextView mYear;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static QuestionEight newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        QuestionEight fragment = new QuestionEight();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_eight, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        mYear = view.findViewById(R.id.year_text_view);
        mYear.setText(Integer.toString(mAnswers.getEight()));

        Button mIncrement = view.findViewById(R.id.increment_year);
        Button mDecrement = view.findViewById(R.id.decrement_year);

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
        int year = Integer.parseInt(mYear.getText().toString());
        if (view.getId() == R.id.decrement_year) {
            year--;
        }
        else {
            year++;
        }

        // Update UI
        mYear.setText(Integer.toString(year));

        // Save choice into the global quiz answers object
        mAnswers.setEight(year);
    }}
