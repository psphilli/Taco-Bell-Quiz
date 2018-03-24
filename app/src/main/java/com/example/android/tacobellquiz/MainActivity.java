package com.example.android.tacobellquiz;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private QuizAnswers answers = new QuizAnswers(this);

    //region Event Handlers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {}
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                onPageChange(position);
            }
        });
    }

    /**
     * Handler for 5 Layer Ingredients checkbox.
     */
    public void onIngredientClick(View view) {
        List<String> ingredients = answers.getSelectedIngredients();
        if (5 < ingredients.size())
        {
            CheckBox checkBox = (CheckBox) view;
            checkBox.setChecked(false);
            Toast.makeText(view.getContext(), R.string.choose_five_help, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handler for which costs more radio group.
     */
    public void onCostMoreClick(View view) {
        ImageView imageView = findViewById(R.id.cost_more_image);
        if (view.getId() == R.id.burrito_supreme_more) {
            imageView.setImageResource(R.drawable.burritosupreme);
        }
        else {
            imageView.setImageResource(R.drawable.mexicanpizza);
        }
    }

    /**
     * Handler for age increment/decrement buttons.
     */
    public void onchangeAgeClick(View view) {
        TextView textView = findViewById(R.id.age_text_view);
        int age = Integer.parseInt(textView.getText().toString());
        if (view.getId() == R.id.decrement_age) {
            age--;
        }
        else {
            age++;
        }
        textView.setText("" + age);
        answers.setSix(age);
    }

    /**
     * Handler for year increment/decrement buttons.
     */
    public void onchangeYearClick(View view) {
        TextView textView = findViewById(R.id.year_text_view);
        int year = Integer.parseInt(textView.getText().toString());
        if (view.getId() == R.id.decrement_year) {
            year--;
        }
        else {
            year++;
        }
        textView.setText("" + year);
        answers.setEight(year);
    }

    /**
     * Handler for backward navigation.
     */
    public void onForwardClick(View view) {
        ViewPager viewpager = findViewById(R.id.viewPager);
        int currentItem = viewpager.getCurrentItem();
        if ((currentItem + 1) < viewpager.getAdapter().getCount())
        {
            viewpager.setCurrentItem((currentItem + 1));
        }
    }

    /**
     * Handler for backward navigation.
     */
    public void onBackwardClick(View view) {
        ViewPager viewpager = findViewById(R.id.viewPager);
        int currentItem = viewpager.getCurrentItem();
        if ((currentItem) > 0)
        {
            viewpager.setCurrentItem((currentItem - 1));
        }
    }

    public void onSubmitResults(View view) {
        String quizSummary = answers.createQuizSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.quiz_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, quizSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *  Validate answer is correctly chosen before allowing page change
     */
    private void onPageChange(int position) {
        ViewPager viewPager = findViewById(R.id.viewPager);

        try {
            switch (position) {
                case (1): {
                    EditText questionOne = findViewById(R.id.crunchy_taco_cost);
                    if (questionOne.getText().toString().length() != 0) {
                        answers.setOne(questionOne.getText().toString());

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
                    } else {
                        viewPager.setCurrentItem(0);
                        Toast.makeText(getApplicationContext(), R.string.price_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.one '" + answers.getOne() + "'");
                    break;
                }

                case (2): {
                    RadioGroup questionTwoRadioGroup = findViewById(R.id.red_sauce_radio_group);
                    int selectedId = questionTwoRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == R.id.yes_red_sauce_name || selectedId == R.id.no_red_sauce_name) {
                        answers.setTwo(selectedId == R.id.yes_red_sauce_name);
                    } else {
                        viewPager.setCurrentItem(1);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.two '" + answers.isTwo() + "'");
                    break;
                }

                case (3): {
                    List<String> ingredients = answers.getSelectedIngredients();
                    if (5 == ingredients.size()) {
                        answers.setThree(ingredients);
                    } else {
                        viewPager.setCurrentItem(2);
                        Toast.makeText(getApplicationContext(), R.string.choose_five_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.three '" + TextUtils.join(",", answers.getThree()) + "'");
                    break;
                }

                case (4): {
                    RadioGroup questionTwoRadioGroup = findViewById(R.id.pregnant_radio_group);
                    int selectedId = questionTwoRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == R.id.yes_when_pregnant || selectedId == R.id.no_when_pregnant) {
                        answers.setFour(selectedId == R.id.yes_when_pregnant);
                    } else {
                        viewPager.setCurrentItem(3);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.four '" + answers.isFour() + "'");
                    break;
                }

                case (5): {
                    RadioGroup questionTwoRadioGroup = findViewById(R.id.cost_more_radio_group);
                    int selectedId = questionTwoRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == R.id.burrito_supreme_more || selectedId == R.id.mexican_pizza_more) {
                        answers.setFive(selectedId == R.id.burrito_supreme_more);
                    } else {
                        viewPager.setCurrentItem(4);
                        Toast.makeText(getApplicationContext(), R.string.costs_more_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.five '" + (answers.isFive() ? "burrito supreme" : "mexican pizza") + "'");
                    break;
                }

                case (6): {
                    Log.i(LOG_TAG, " answers.six'" + answers.getSix() + "'");
                    break;
                }

                case (7): {
                    RadioGroup questionTwoRadioGroup = findViewById(R.id.illuminati_radio_group);
                    int selectedId = questionTwoRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == R.id.yes_illuminati || selectedId == R.id.no_illuminati) {
                        answers.setSeven(selectedId == R.id.yes_illuminati);
                    } else {
                        viewPager.setCurrentItem(5);
                        Toast.makeText(getApplicationContext(), R.string.yes_no_help, Toast.LENGTH_SHORT).show();
                    }
                    Log.i(LOG_TAG, " answers.seven '" + answers.isSeven() + "'");
                    break;
                }

                case (8): {
                    Log.i(LOG_TAG, " answers.eight '" + answers.getEight() + "'");

                    int numCorrect = answers.getNumberCorrect();
                    TextView submitTextView = findViewById(R.id.submit_text_view);
                    submitTextView.setText(getString(R.string.submit_all_correct_text, numCorrect));
                    if (numCorrect == 8) {
                        onSubmitResults(null);
                    }
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

    public String getGidgetAge()
    {
        return String.valueOf(answers.getSix());
    }

    public String getYearQuesadilla()
    {
        return String.valueOf(answers.getEight());
    }

    /**
     * Internal implementation of FragmentPagerAdapter
     */
    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return new QuestionOne();
                case 1: return new QuestionTwo();
                case 2: return new QuestionThree();
                case 3: return new QuestionFour();
                case 4: return new QuestionFive();
                case 5: return new QuestionSix();
                case 6: return new QuestionSeven();
                case 7: return new QuestionEight();
                case 8: return new Submit();
                default: return new QuestionOne();
            }
        }

        @Override
        public int getCount() {
            return 9;
        }
    }
}
