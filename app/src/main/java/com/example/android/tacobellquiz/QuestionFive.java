package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * Question Five - Which costs more Burrito Supreme or Mexican Pizza?    mexican pizza
 */

public class QuestionFive extends Fragment implements View.OnClickListener {
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;
    private ImageView mImageView;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static QuestionFive newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        QuestionFive fragment = new QuestionFive();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_five, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        RadioButton mYes = view.findViewById(R.id.burrito_supreme_more);
        RadioButton mNo = view.findViewById(R.id.mexican_pizza_more);
        mImageView = view.findViewById(R.id.cost_more_image);

        // Using class Boolean so that null will represent unset state
        if (mAnswers.getFive() != null) {
            if (mAnswers.getFive()) {
                mYes.setChecked(true);
                mImageView.setImageResource(R.drawable.burritosupreme);
            } else {
                mNo.setChecked(true);
                mImageView.setImageResource(R.drawable.mexicanpizza);
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
        mAnswers.setFive(view.getId() == R.id.burrito_supreme_more);

        if (mAnswers.getFive()) {
            mImageView.setImageResource(R.drawable.burritosupreme);
        }
        else {
            mImageView.setImageResource(R.drawable.mexicanpizza);
        }
    }
}
