package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Report number correct, allow the user to quit the app
 */

public class Results extends Fragment {
    private final String LOG_TAG = Results.class.getSimpleName();
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static Results newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        Results fragment = new Results();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        return view;
    }

    /**
     * Populate the results with the persisted answers, manage
     * left chevron visibility
     *
     * @param isVisibleToUser True when the fragment becomes visible to the user
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(LOG_TAG, "isVisibleToUser");

            Toast toast = Toast.makeText(getContext(), mAnswers.getQuizResultSummary(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            TextView resultDetail = getView().findViewById(R.id.results_detail);
            resultDetail.setText(mAnswers.getQuizResultDetail());
        }
        else {
            Log.i(LOG_TAG, "not isVisibleToUser");
        }
    }
}
