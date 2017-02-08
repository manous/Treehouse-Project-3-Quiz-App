package com.example.kore.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizApp extends AppCompatActivity {
    private QuestionBank mQuestionBank = new QuestionBank();
    private TextView mOperationTextView;
    private TextView mProgressTextView;
    private Button mNextButton;
    private RadioGroup mRadioGroup;
    private RadioButton mCorrectAnswersRadioButton;
    private RadioButton mFirstIncorrectAnswersRadioButton;
    private RadioButton mSecondIncorrectAnswersRadioButton;
    private int correct;
    private int attempt;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);
        correct=0;
        attempt=0;

        mOperationTextView = (TextView) findViewById(R.id.operationTextView);
        mProgressTextView  = (TextView) findViewById(R.id.progressTextView);

        mNextButton = (Button) findViewById(R.id.nextButton);
        mRadioGroup = (RadioGroup)findViewById(R.id.answerButton);
        mCorrectAnswersRadioButton = (RadioButton)findViewById(R.id.correctAnswersRadioButton);
        mFirstIncorrectAnswersRadioButton = (RadioButton) findViewById(R.id.firstIncorrectAnswersRadioButton);
        mSecondIncorrectAnswersRadioButton = (RadioButton) findViewById(R.id.secondIncorrectAnswersRadioButton);

        mCorrectAnswersRadioButton.setEnabled(false);
        mFirstIncorrectAnswersRadioButton.setEnabled(false);
        mSecondIncorrectAnswersRadioButton.setEnabled(false);

         View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attempt == mQuestionBank.leftAdders.length){
                    attempt=0;
                    correct=0;
                }
                attempt ++;

             String operation = mQuestionBank.getOperation();
             int answer= mQuestionBank.getCorrectAnswers();
             int firstAns = mQuestionBank.getFirstIncorrectCorrectAnswers();
             int secondAns = mQuestionBank.getSecondIncorrectCorrectAnswers();

             mOperationTextView.setText(operation);
             mNextButton.setText(R.string.nextButton);
             mCorrectAnswersRadioButton.setText(answer+"");
             mFirstIncorrectAnswersRadioButton.setText(firstAns+"");
             mSecondIncorrectAnswersRadioButton.setText(secondAns+"");

                mCorrectAnswersRadioButton.setEnabled(true);
                mFirstIncorrectAnswersRadioButton.setEnabled(true);
                mSecondIncorrectAnswersRadioButton.setEnabled(true);

                 if(mRadioGroup.getCheckedRadioButtonId()>0){
                     mRadioGroup.clearCheck();
                 }
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.correctAnswersRadioButton:
                                correct++;
                                Toast.makeText(QuizApp.this, "Correct!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.firstIncorrectAnswersRadioButton:
                                Toast.makeText(QuizApp.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.secondIncorrectAnswersRadioButton:
                                Toast.makeText(QuizApp.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                                break;

                        }
                       mRadioGroup.setOnCheckedChangeListener(null);
                    }

                });
                String progress =  mQuestionBank.trackProgress(correct,attempt);
                mProgressTextView.setText(progress);
            }
        };
        mNextButton.setOnClickListener(listener);

    }
}
