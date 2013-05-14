package com.huskysoft.eduki;

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
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizzesViewActivity extends Activity implements TaskComplete {

    /** 
     * questions - Questions that will be asked
     * quiz - Specific quiz this view is tied to 
     * course_id - ID of the course that this quiz is tied to
     */
    private List<Question> questionList;
    private Quiz quiz;
    private RadioGroup answers;
    private Button submitButton;
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
        questionList = QuizQuery.parseQuestionsList(data);
        this.setTitle(quiz.getTitle());
        
        // Check if there are questions for this quiz, if so then begin the quiz.
        // Otherwise then display a message that there is no quiz content
        if (questionList.size() == 0) {
            setContentView(R.layout.activity_no_list_found);
            TextView contentView = (TextView) findViewById(R.id.noListText);
            contentView.setText("No questions found for this quiz.");
        } else {
            setContentView(R.layout.activity_quizzesview);
            this.setTitle(quiz.getTitle());
            updateQuiz();
        }
    }
    
    private void updateQuiz() {
        if (questionsAnswered == questionList.size()) { // Finished the quiz
            // TODO: Display results
        } else { // Show the question
            TextView contentView = (TextView) findViewById(R.id.questionText);
            contentView.setText("Question goes here"); //TODO: UPDATE
            
            generateAnswers();
        }
    }
    
    private void generateAnswers() {
        answers = (RadioGroup) findViewById(R.id.answerGroup);
        submitButton = (Button) findViewById(R.id.submitButton);
        
        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
}
