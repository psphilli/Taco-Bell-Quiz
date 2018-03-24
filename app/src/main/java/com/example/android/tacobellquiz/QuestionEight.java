package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by psphilli on 2/11/2018.
 */
public class QuestionEight extends Fragment {

    private final String LOG_TAG = QuestionEight.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_eight, container, false);

        TextView textView = view.findViewById(R.id.year_text_view);
        textView.setText(((MainActivity) getActivity()).getYearQuesadilla());

        return view;
    }
}
