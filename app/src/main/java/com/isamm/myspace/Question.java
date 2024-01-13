package com.isamm.myspace;

public class Question {
    // answerResId will store question
    private int questionTextID;
    // answerTrue will store correct answer
    // of the question provided
    private String correctAnswer;
    private String [] options;

    public Question(int questionTextID, String correctAnswer,String []options)
    {
        // setting the values through
        // arguments passed in constructor
        this.questionTextID = questionTextID;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    // returning the question passed
    public int getQuestionTextID()
    {
        return questionTextID;
    }

    // setting the question passed
    public void setQuestionTextID(int questionTextID)
    {
        this.questionTextID = questionTextID;
    }

    // returning true if it's the correct answer
    // of question
    public boolean isCorrectAnswer(String answer)
    {
        return correctAnswer.equals(answer);
    }

    public String getOptionByIndex(int i){
        return options[i];
    }
    // setting the correct
    // answer of question
    public void setCorrectAnswer(String correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }
}
