package com.huskysoft.eduki;

import java.util.ArrayList;
import java.util.List;

import com.huskysoft.eduki.data.Question;
import com.huskysoft.eduki.data.Quiz;
import com.huskysoft.eduki.data.QuizQuery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizzesViewActivity extends Activity implements TaskComplete {

    /** 
     * questions - Questions that will be asked
     * quiz - Specific quiz this view is tied to 
     * course_id - ID of the course that this quiz is tied to
     */
    private List<String> questions;
    private List<List<String>> choices;
    private List<String> answers;
    private Quiz quiz;
    private int course_id;
    private static int questionsAnswered = 0; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String quiz_title = extras.getString("quiz_title");
            int quiz_id = extras.getInt("quiz_id");
            course_id = extras.getInt("course_id");
            quiz = new Quiz(quiz_id, quiz_title);
            QuizQuery.getAllQuestions(this, course_id, quiz_id);
        }
        setContentView(R.layout.loading_screen);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void taskComplete(String data) {
        //TODO: Implement taking quiz
        // Get the list of questions, and set the title
        List<Question> questionList = QuizQuery.parseQuestionsList(data);
        this.setTitle(quiz.getTitle());
        
        // Check if there are questions for this quiz, if so then begin the quiz.
        // Otherwise then display a message that there is no quiz content
        if (questionList.size() == 0) {
            setContentView(R.layout.activity_no_list_found);
            TextView contentView = (TextView) findViewById(R.id.noListText);
            contentView.setText("No questions found for this quiz.");
        } else {
            questions = new ArrayList<String>();
            answers = new ArrayList<String>();
            choices = new ArrayList<List<String>>();
            
            parseQuizContent(questionList);
            setContentView(R.layout.activity_quizzesview);
            updateQuiz();
        }
    }
    
    public void parseQuizContent(List<Question> questionList) {
        for (Question q: questionList) {
            String[] questionContent = q.getQuestion().split("\\n");
            questions.add(questionContent[0]);
            answers.add(q.getAnswer());
            
            List<String> answerChoices = new ArrayList<String>();
            for (int i = 1; i < questionContent.length; i++) {
                answerChoices.add(questionContent[i]);
            }
            choices.add(answerChoices);
        }
    }
    
    private void updateQuiz() {
        if (questionsAnswered == questions.size()) { // Finished the quiz
            // TODO: Display results
            setContentView(R.layout.activity_no_list_found);
            TextView contentView = (TextView) findViewById(R.id.noListText);
            contentView.setText("Quiz Finished!");
        } else { // Show the question
            TextView contentView = (TextView) findViewById(R.id.questionText);
            contentView.setText(questions.get(questionsAnswered)); 
            generateAnswers();
            questionsAnswered++;
        }
    }
    
    private void generateAnswers() {
        RadioGroup answersRadioGroup = (RadioGroup) findViewById(R.id.answerGroup);
        answersRadioGroup.removeAllViews(); // Refresh the display
        List<String> currentChoices = choices.get(questionsAnswered);
        RadioButton[] rb = new RadioButton[currentChoices.size()];
        for (int i = 0; i < currentChoices.size(); i++) {
            rb[i] = new RadioButton(this);
            rb[i].setText(currentChoices.get(i));
            rb[i].setId(i);
            answersRadioGroup.addView(rb[i]);
        }

        Button submitButton = (Button) findViewById(R.id.submitButton);        
        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: Add score tracking
                updateQuiz();
            }
        });
    }
}
