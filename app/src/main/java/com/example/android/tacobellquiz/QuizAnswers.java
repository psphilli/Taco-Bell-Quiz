package com.example.android.tacobellquiz;

import android.app.Activity;
import android.util.Log;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for managing quiz answers
 */
public class QuizAnswers
{
    private final String LOG_TAG = QuizAnswers.class.getSimpleName();

    //region Fields & Properties

    private Activity activity;
    private String one;
    private boolean two;
    private List<String> three = new ArrayList<String>();
    private boolean four;
    private boolean five;
    private int six = 10;
    private boolean seven;
    private int eight = 1997;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public boolean isTwo() {
        return two;
    }

    public void setTwo(boolean two) {
        this.two = two;
    }

    public List<String> getThree() {
        return three;
    }

    public void setThree(List<String> three) {
        this.three = three;
    }

    public boolean isFour() {
        return four;
    }

    public void setFour(boolean four) {
        this.four = four;
    }

    public boolean isFive() {
        return five;
    }

    public void setFive(boolean five) {
        this.five = five;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public boolean isSeven() {
        return seven;
    }

    public void setSeven(boolean seven) {
        this.seven = seven;
    }

    public int getEight() {
        return eight;
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    //endregion

    public QuizAnswers(Activity activity) {
        this.activity = activity;
    }

    /**
     * Gets the number of correctly answered questions
     *
     * @Returns the number of correctly answered questions
     */
    public int getNumberCorrect() {
        int correct = 0;

        if (isOneCorrect()) correct++;
        if (isTwoCorrect()) correct++;
        if (isThreeCorrect()) correct++;
        if (isFourCorrect()) correct++;
        if (isFiveCorrect()) correct++;
        if (isSixCorrect()) correct++;
        if (isSevenCorrect()) correct++;
        if (isEightCorrect()) correct++;

        Log.i(LOG_TAG, "getNumberCorrect: '" + correct + "'");

        return correct;
    }

    /**
     * Get list of ingredients.
     *
     * @Returns a list of ingredients as strings
     */
    public List<String> getSelectedIngredients() {

        CheckBox sourCream = activity.findViewById(R.id.sour_cream);
        CheckBox jalapenoSauce = activity.findViewById(R.id.jalapeno_sauce);
        CheckBox redSauce = activity.findViewById(R.id.red_sauce);
        CheckBox nachoCheese = activity.findViewById(R.id.nacho_cheese);
        CheckBox beans = activity.findViewById(R.id.beans);
        CheckBox beef = activity.findViewById(R.id.beef);
        CheckBox cheese = activity.findViewById(R.id.cheese);
        CheckBox onion = activity.findViewById(R.id.onion);

        List<String> ingredients = new ArrayList<String>(6);

        if (sourCream.isChecked())
            ingredients.add("Sour Cream");
        if (jalapenoSauce.isChecked())
            ingredients.add("Jalapeno Sauce");
        if (redSauce.isChecked())
            ingredients.add("Red Sauce");
        if (nachoCheese.isChecked())
            ingredients.add("Nacho Cheese");
        if (beans.isChecked())
            ingredients.add("Beans");
        if (beef.isChecked())
            ingredients.add("Beef");
        if (cheese.isChecked())
            ingredients.add("Cheese");
        if (onion.isChecked())
            ingredients.add("Onion");

        return ingredients;
    }


    public String createQuizSummary()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(activity.getString(R.string.answer_one, (isOneCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_two, (isTwoCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_three, (isThreeCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_four, (isFourCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_five, (isFiveCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_six, (isSixCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_seven, (isSevenCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");
        sb.append(activity.getString(R.string.answer_eight, (isEightCorrect() ? "CORRECT" : "INCORRECT")) + "\n\n");

        return sb.toString();
    }

    //region Helpers

    /**
     * Determines if question one is correct
     *
     * @Returns true if the answer to question one is correct
     */
    private boolean isOneCorrect()
    {
        return (one.equals("1.29"));
    }

    /**
     * Determines if question two is correct
     *
     * @Returns true if the answer to question two is correct
     */
    private boolean isTwoCorrect()
    {
        return (!two);
    }

    /**
     * Determines if question three is correct
     *
     * @Returns true if the answer to question three is correct
     */
    private boolean isThreeCorrect() {
        return (three.containsAll(new ArrayList<String>(Arrays.asList(
                new String[] { "Beef", "Beans", "Cheese", "Nacho Cheese", "Sour Cream" }))));
    }

    /**
     * Determines if question four is correct
     *
     * @Returns true if the answer to question four is correct
     */
    private boolean isFourCorrect()
    {
        return (four);
    }

    /**
     * Determines if question five is correct
     *
     * @Returns true if the answer to question five is correct
     */
    private boolean isFiveCorrect()
    {
        return (!five);
    }

    /**
     * Determines if question six is correct
     *
     * @Returns true if the answer to question six is correct
     */
    private boolean isSixCorrect()
    {
        return (six == 15);
    }

    /**
     * Determines if question seven is correct
     *
     * @Returns true if the answer to question seven is correct
     */
    private boolean isSevenCorrect()
    {
        return (seven);
    }

    /**
     * Determines if question eight is correct
     *
     * @Returns true if the answer to question eight is correct
     */
    private boolean isEightCorrect()
    {
        return (eight == 2002);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "One: " + one + " Two: " + two + " Three: " + three
                + " Four: " + four + " Five: " + five + " Six: " + six
                + " Seven: " + seven + " Eight: " + eight;
    }

    //endregion
}
