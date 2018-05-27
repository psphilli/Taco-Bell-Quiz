package com.example.android.tacobellquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Question Three - What are the ingredients in the Beefy 5 Layer Burrito?     Beef, Beans, Cheese, Sour Cream and Nacho Cheese
 */

public class QuestionThree extends Fragment implements View.OnClickListener {
    private final static String ANSWER_ARGUMENT = "answers";
    private QuizAnswers mAnswers;

    private CheckBox mSourCream;
    private CheckBox mJalapenoSauce;
    private CheckBox mRedSauce;
    private CheckBox mNachoCheese;
    private CheckBox mBeans;
    private CheckBox mBeef;
    private CheckBox mCheese;
    private CheckBox mOnion;

    /**
     * Pass the given quiz answers reference argument to a new fragment
     *
     * @param answers is a reference to the global quiz answers object
     * @return the new fragment
     */
    public static QuestionThree newInstance(QuizAnswers answers) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ANSWER_ARGUMENT, answers);

        QuestionThree fragment = new QuestionThree();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_three, container, false);

        mAnswers = getArguments().getParcelable(ANSWER_ARGUMENT);

        // Set checkbox checked, create onClick listener
        // NOTE WEIRD BEHAVIOR - After the page gets recycled and restored, the "box" part of the
        // selected checkboxes is accent colored but not filled, and the value is false.
        // The (8 .setChecked) logic below is the work-around to ensure selections are retained
        mSourCream = view.findViewById(R.id.sour_cream);
        mSourCream.setChecked(mAnswers.getThree().contains(getString(R.string.sour_cream)));
        mSourCream.setOnClickListener(this);

        mJalapenoSauce = view.findViewById(R.id.jalapeno_sauce);
        mJalapenoSauce.setChecked(mAnswers.getThree().contains(getString(R.string.jalapeno_sauce)));
        mJalapenoSauce.setOnClickListener(this);

        mRedSauce = view.findViewById(R.id.red_sauce);
        mRedSauce.setChecked(mAnswers.getThree().contains(getString(R.string.red_sauce)));
        mRedSauce.setOnClickListener(this);

        mNachoCheese = view.findViewById(R.id.nacho_cheese);
        mNachoCheese.setChecked(mAnswers.getThree().contains(getString(R.string.nacho_cheese)));
        mNachoCheese.setOnClickListener(this);

        mBeans = view.findViewById(R.id.beans);
        mBeans.setChecked(mAnswers.getThree().contains(getString(R.string.beans)));
        mBeans.setOnClickListener(this);

        mBeef = view.findViewById(R.id.beef);
        mBeef.setChecked(mAnswers.getThree().contains(getString(R.string.beef)));
        mBeef.setOnClickListener(this);

        mCheese = view.findViewById(R.id.cheese);
        mCheese.setChecked(mAnswers.getThree().contains(getString(R.string.cheese)));
        mCheese.setOnClickListener(this);

        mOnion = view.findViewById(R.id.onion);
        mOnion.setChecked(mAnswers.getThree().contains(getString(R.string.onion)));
        mOnion.setOnClickListener(this);

        return view;
    }

    /**
     * Handler for all the CheckBoxes in the Fragment
     *
     * @param view The CheckBox which generated the event
     */
    @Override
    public void onClick(View view) {
        // Save selections into the global quiz answers object
        mAnswers.setThree(getSelectedIngredients());
    }

    /**
     * Generate a list of strings for the check boxes that have been selected
     */
    private List<String> getSelectedIngredients() {
        List<String> ingredients = new ArrayList<>(8);

        if (mSourCream.isChecked())
            ingredients.add(getString(R.string.sour_cream));
        if (mJalapenoSauce.isChecked())
            ingredients.add(getString(R.string.jalapeno_sauce));
        if (mRedSauce.isChecked())
            ingredients.add(getString(R.string.red_sauce));
        if (mNachoCheese.isChecked())
            ingredients.add(getString(R.string.nacho_cheese));
        if (mBeans.isChecked())
            ingredients.add(getString(R.string.beans));
        if (mBeef.isChecked())
            ingredients.add(getString(R.string.beef));
        if (mCheese.isChecked())
            ingredients.add(getString(R.string.cheese));
        if (mOnion.isChecked())
            ingredients.add(getString(R.string.onion));

        return ingredients;
    }
}