package com.example.android.tacobellquiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final static String QUIZ_ANSWER_BUNDLE= "answers";
    private ViewPager mViewPager;
    private QuizAnswers mAnswers = new QuizAnswers();
    private ImageView mLeftNav;
    private ImageView mRightNav;

    //region Overrides

    /**
     * Creates the main activity
     *
     * @param savedInstanceState contains the serialized answers object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mLeftNav = findViewById(R.id.left_nav);
        mRightNav = findViewById(R.id.right_nav);
        mLeftNav.setVisibility(View.GONE);

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {}
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                onPageChange(position);
            }
        });

        // Set onClick listener for forward (right) navigation chevron
        mRightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = mViewPager.getCurrentItem();
                if ((currentItem + 1) < mViewPager.getAdapter().getCount())
                {
                    mViewPager.setCurrentItem((currentItem + 1));
                }
            }
        });

        // Set onClick listener for back (left) navigation chevron
        mLeftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = mViewPager.getCurrentItem();
                if ((currentItem) > 0)
                {
                    mViewPager.setCurrentItem((currentItem - 1));
                }
            }
        });
    }

    /**
     * Save all appropriate fragment state data.
     *
     * @param outState contains the serialized answers object
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(QUIZ_ANSWER_BUNDLE, mAnswers);

        super.onSaveInstanceState(outState);
    }

    /**
     * Return all appropriate fragment state data.
     *
     * @param savedInstanceState contains the serialized answers object
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mAnswers = savedInstanceState.getParcelable(QUIZ_ANSWER_BUNDLE);
    }

    //endregion

    //region Event Handlers

    /**
     *  Validate answer is correctly chosen before allowing page change
     */
    private void onPageChange(int position) {

        try {
            Log.i(LOG_TAG, "position: " + Integer.toString(position));

            mLeftNav.setVisibility(View.VISIBLE);
            mRightNav.setVisibility(View.VISIBLE);

            switch (position) {
                case (0): {
                    mLeftNav.setVisibility(View.GONE);
                }

                case (1): {
                    EditText questionOne = findViewById(R.id.crunchy_taco_cost);
                    if (questionOne.getText().toString().length() != 0) {
                        mAnswers.setOne(questionOne.getText().toString());

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mViewPager.getWindowToken(), 0);
                    } else {
                        mViewPager.setCurrentItem(0);
                        Toast.makeText(getApplicationContext(), R.string.price_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.one '" + mAnswers.getOne() + "'");
                    break;
                }

                case (2): {
                    // Catch unset state
                    if (mAnswers.getTwo() == null) {
                        mViewPager.setCurrentItem(1);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.two '" + mAnswers.getTwo() + "'");
                    break;
                }

                case (3): {
                    if (5 != mAnswers.getThree().size()) {
                        mViewPager.setCurrentItem(2);
                        Toast.makeText(getApplicationContext(), R.string.choose_five_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.three '" + TextUtils.join(",", mAnswers.getThree()) + "'");
                    break;
                }

                case (4): {
                    // Catch unset state
                    if (mAnswers.getFour() == null) {
                        mViewPager.setCurrentItem(3);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.four '" + mAnswers.getFour() + "'");
                    break;
                }

                case (5): {
                    // Catch unset state
                    if (mAnswers.getFive() == null) {
                        mViewPager.setCurrentItem(4);
                        Toast.makeText(getApplicationContext(), R.string.costs_more_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.five '" + (mAnswers.getFive() ? "burrito supreme" : "mexican pizza") + "'");
                    break;
                }

                case (6): {
                    Log.i(LOG_TAG, " mAnswers.six '" + mAnswers.getSix() + "'");
                    break;
                }

                case (7): {
                    // Catch unset state
                    if (mAnswers.getSeven() == null) {
                        mViewPager.setCurrentItem(6);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " mAnswers.seven '" + mAnswers.getSeven() + "'");
                    break;
                }

                case (8): {
                    Log.i(LOG_TAG, " mAnswers.eight '" + mAnswers.getEight() + "'");
                    mRightNav.setVisibility(View.GONE);
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            Log.e(LOG_TAG, ex.toString());
        }
    }

    //endregion

    //region Internal Class PageAdapter

    /**
     * Internal implementation of FragmentPagerAdapter
     */
    private class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return new QuestionOne();
                case 1: return QuestionTwo.newInstance(mAnswers);
                case 2: return QuestionThree.newInstance(mAnswers);
                case 3: return QuestionFour.newInstance(mAnswers);
                case 4: return QuestionFive.newInstance(mAnswers);
                case 5: return QuestionSix.newInstance(mAnswers);
                case 6: return QuestionSeven.newInstance(mAnswers);
                case 7: return QuestionEight.newInstance(mAnswers);
                case 8: return Results.newInstance(mAnswers);
                default: return new QuestionOne();
            }
        }

        @Override
        public int getCount() {
            return 9;
        }
    }

    //endregion
}
