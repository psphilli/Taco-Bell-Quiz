package com.example.android.tacobellquiz;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for managing quiz answers
 */
public class QuizAnswers implements Parcelable
{
    private final String LOG_TAG = QuizAnswers.class.getSimpleName();

    //region Fields & Properties

    private String one;
    private Boolean two;
    private List<String> three = new ArrayList<>();
    private Boolean four;
    private Boolean five;
    private int six = Integer.parseInt(TacoBellQuizApplication.getContext().getString(R.string.initial_age));
    private Boolean seven;
    private int eight = Integer.parseInt(TacoBellQuizApplication.getContext().getString(R.string.initial_year));

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public Boolean getTwo() {
        return two;
    }

    public void setTwo(boolean two) { this.two = two; }

    private void setTwo(byte b) { this.two = setNullableBoolFromByte(b); }

    public List<String> getThree() {
        return three;
    }

    public void setThree(List<String> three) {
        this.three = three;
    }

    public Boolean getFour() {
        return four;
    }

    public void setFour(boolean four) {
        this.four = four;
    }

    private void setFour(byte b) { this.four = setNullableBoolFromByte(b); }

    public Boolean getFive() {
        return five;
    }

    public void setFive(boolean five) {
        this.five = five;
    }

    private void setFive(byte b) { this.five = setNullableBoolFromByte(b); }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public Boolean getSeven() {
        return seven;
    }

    public void setSeven(boolean seven) {
        this.seven = seven;
    }

    private void setSeven(byte b) { this.seven = setNullableBoolFromByte(b); }

    public int getEight() {
        return eight;
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    //endregion

    //region Constructors

    /**
     * Empty constructor used during initial object creation
     */
    public QuizAnswers() {
    }

    /**
     * Constructor for retaining the users previously selected answers
     *
     * @param parcel is the serialized version of the previously selected answers
     */
    private QuizAnswers(Parcel parcel) {
        setOne(parcel.readString());
        setTwo(parcel.readByte());
        setThree(parcel.createStringArrayList());
        setFour(parcel.readByte());
        setFive(parcel.readByte());
        setSix(parcel.readInt());
        setSeven(parcel.readByte());
        setEight(parcel.readInt());
    }

    //endregion

    //region Implementation of Parcelable

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param parcel  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getOne());
        parcel.writeByte(getByteFromNullableBool(getTwo()));
        parcel.writeStringList(getThree());
        parcel.writeByte(getByteFromNullableBool(getFour()));
        parcel.writeByte(getByteFromNullableBool(getFive()));
        parcel.writeInt(getSix());
        parcel.writeByte(getByteFromNullableBool(getSeven()));
        parcel.writeInt(getEight());
    }


    /**
     * Used for the binder to reconstruct the parcel
     */
    public final static Parcelable.Creator<QuizAnswers> CREATOR = new Parcelable.Creator<QuizAnswers>() {
        @Override
        public QuizAnswers createFromParcel(Parcel parcel) {
            return new QuizAnswers(parcel);
        }

        @Override
        public QuizAnswers[] newArray(int i) {
            return new QuizAnswers[i];
        }
    };

    //endregion

    //region Methods

    /**
     * Gets summary of results of the answered questions
     *
     * @return A description of the users answers whether correct or incorrect
     */
    public String getQuizResultSummary()
    {
        String summary = TacoBellQuizApplication.getContext().getString(R.string.result_summary_text, getNumberCorrect());

        Log.i(LOG_TAG, "getQuizResultSummary: \n" + summary);

        return summary;
    }

    /**
     * Gets result details of the answered questions
     *
     * @return A description of the users answers whether correct or incorrect
     */
    public String getQuizResultDetail()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_one, (isOneCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_two, (isTwoCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_three, (isThreeCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_four, (isFourCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_five, (isFiveCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_six, (isSixCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_seven, (isSevenCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");
        sb.append(TacoBellQuizApplication.getContext().getString(R.string.answer_eight, (isEightCorrect() ? "CORRECT" : "INCORRECT"))).append("\n\n");

        Log.i(LOG_TAG, "getQuizResultDetail: \n" + sb.toString());

        return sb.toString();
    }

    /**
     * Create a string representation of the fields in the object
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

    //region Helpers

    /**
     * Determines if question one is correct
     *
     * @return true if the answer to question one is correct
     */
    private boolean isOneCorrect()
    {
        return (one.equals("1.29"));
    }

    /**
     * Determines if question two is correct
     *
     * @return true if the answer to question two is correct
     */
    private boolean isTwoCorrect()
    {
        return (!(getByteFromNullableBool(two) == 1));
    }

    /**
     * Determines if question three is correct
     *
     * @return true if the answer to question three is correct
     */
    private boolean isThreeCorrect() {
        return (three.containsAll(new ArrayList<>(Arrays.asList(
                TacoBellQuizApplication.getContext().getString(R.string.beef),
                TacoBellQuizApplication.getContext().getString(R.string.beans),
                TacoBellQuizApplication.getContext().getString(R.string.cheese),
                TacoBellQuizApplication.getContext().getString(R.string.nacho_cheese),
                TacoBellQuizApplication.getContext().getString(R.string.sour_cream)))));
    }

    /**
     * Determines if question four is correct
     *
     * @return true if the answer to question four is correct
     */
    private boolean isFourCorrect()
    {
        return (getByteFromNullableBool(four) == 1);
    }

    /**
     * Determines if question five is correct
     *
     * @return true if the answer to question five is correct
     */
    private boolean isFiveCorrect()
    {
        return (!(getByteFromNullableBool(five) == 1));
    }

    /**
     * Determines if question six is correct
     *
     * @return true if the answer to question six is correct
     */
    private boolean isSixCorrect()
    {
        return (six == 15);
    }

    /**
     * Determines if question seven is correct
     *
     * @return true if the answer to question seven is correct
     */
    private boolean isSevenCorrect()
    {
        return (getByteFromNullableBool(seven) == 1);
    }

    /**
     * Determines if question eight is correct
     *
     * @return true if the answer to question eight is correct
     */
    private boolean isEightCorrect()
    {
        return (eight == 2002);
    }

    /**
     * Gets the number of correctly answered questions
     *
     * @return the number of correctly answered questions
     */
    private int getNumberCorrect() {
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
     * Convert three state Boolean to byte
     * @param bool is a nullable Boolean
     * @return -1 if null, 0 if false, 1 if true
     */
    private byte getByteFromNullableBool(Boolean bool)
    {
        if (bool == null)
            return -1;

        return (byte)(bool ? 1 : 0);
    }

    /**
     * Convert a byte to a three state Boolean
     * @param b is a byte whose value is -1 if Boolean is null, 0 if false, 1 if true
     * @return the proper Boolean value
     */
    private Boolean setNullableBoolFromByte(byte b)
    {
        if (b == -1)
            return null;

        return b != 0;
    }

    //endregion
}
