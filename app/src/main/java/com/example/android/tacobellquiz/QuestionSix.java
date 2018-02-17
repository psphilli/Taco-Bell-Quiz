package com.example.android.tacobellquiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by psphilli on 2/11/2018.
 */

public class QuestionSix extends Fragment {

    private final String LOG_TAG = QuestionSix.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.question_six, container, false);

        TextView textView = view.findViewById(R.id.age_text_view);
        textView.setText(((MainActivity) getActivity()).getGidgetAge());

        return view;
    }
}
