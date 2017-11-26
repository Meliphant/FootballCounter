package com.example.android.courtcounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Set global variables for TextViews which show scores and counters for each type of score (goals, red cards, yellow cards)
    TextView scoreTeamA, scoreRedTeamA, scoreYellowTeamA, btnGoalTeamA, btnRedTeamA, btnYellowTeamA,
            scoreTeamB, scoreRedTeamB, scoreYellowTeamB, btnGoalTeamB, btnRedTeamB, btnYellowTeamB,
            btnReset;
    int scoreCntA = 0, scoreCntB = 0, yellowCntA = 0, yellowCntB = 0, redCntA = 0, redCntB = 0;

    // Set variables for cursor visibility logic
    EditText nameTeamA, nameTeamB;

    // Set default variables for reset action
    String hintTeamA = "", hintTeamB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define global variables by views' id
        scoreTeamA = (TextView) findViewById(R.id.team_a_score);
        scoreRedTeamA = (TextView) findViewById(R.id.team_a_red);
        scoreYellowTeamA = (TextView) findViewById(R.id.team_a_yellow);
        btnGoalTeamA = (TextView) findViewById(R.id.team_a_goal_btn);
        btnRedTeamA = (TextView) findViewById(R.id.team_a_red_btn);
        btnYellowTeamA = (TextView) findViewById(R.id.team_a_yellow_btn);

        scoreTeamB = (TextView) findViewById(R.id.team_b_score);
        scoreRedTeamB = (TextView) findViewById(R.id.team_b_red);
        scoreYellowTeamB = (TextView) findViewById(R.id.team_b_yellow);
        btnGoalTeamB = (TextView) findViewById(R.id.team_b_goal_btn);
        btnRedTeamB = (TextView) findViewById(R.id.team_b_red_btn);
        btnYellowTeamB = (TextView) findViewById(R.id.team_b_yellow_btn);

        btnReset = (Button) findViewById(R.id.reset);

        nameTeamA = (EditText) findViewById(R.id.team_a_name);
        nameTeamB = (EditText) findViewById(R.id.team_b_name);

        // Set OnClickListener on the buttons
        btnGoalTeamA.setOnClickListener(this);
        btnRedTeamA.setOnClickListener(this);
        btnYellowTeamA.setOnClickListener(this);

        btnGoalTeamB.setOnClickListener(this);
        btnRedTeamB.setOnClickListener(this);
        btnYellowTeamB.setOnClickListener(this);

        btnReset.setOnClickListener(this);

        nameTeamA.setOnClickListener(this);
        nameTeamB.setOnClickListener(this);

        nameTeamA.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                nameTeamA.setCursorVisible(false);
                if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(nameTeamA.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
        nameTeamB.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                nameTeamB.setCursorVisible(false);
                if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(nameTeamB.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
    }

    // Click listener for the button views
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.team_a_goal_btn:
                scoreCntA ++;
                display();
                break;
            case R.id.team_b_goal_btn:
                scoreCntB ++;
                display();
                break;
            case R.id.team_a_yellow_btn:
                yellowCntA ++;
                display();
                break;
            case R.id.team_b_yellow_btn:
                yellowCntB ++;
                display();
                break;
            case R.id.team_a_red_btn:
                redCntA ++;
                display();
                break;
            case R.id.team_b_red_btn:
                redCntB ++;
                display();
                break;
            case R.id.reset:
                scoreCntA = 0;
                scoreCntB = 0;
                yellowCntA = 0;
                yellowCntB = 0;
                redCntA = 0;
                redCntB = 0;
                display();
                displayTeamName();
                break;
            // Make cursor visible when click on EditText view
            case R.id.team_a_name:
                nameTeamA.setCursorVisible(true);
                break;
            case R.id.team_b_name:
                nameTeamB.setCursorVisible(true);
                break;
        }
    }

    // Display all scores
    public void display() {
        scoreTeamA.setText(String.valueOf(scoreCntA));
        scoreRedTeamA.setText(String.valueOf(redCntA));
        scoreYellowTeamA.setText(String.valueOf(yellowCntA));
        scoreTeamB.setText(String.valueOf(scoreCntB));
        scoreRedTeamB.setText(String.valueOf(redCntB));
        scoreYellowTeamB.setText(String.valueOf(yellowCntB));
    }
    public void displayTeamName() {
        nameTeamA.setText(String.valueOf(hintTeamA));
        nameTeamB.setText(String.valueOf(hintTeamB));
    }
    // Save current scores after click
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("scoreCntA", scoreCntA);
        outState.putInt("scoreCntB", scoreCntB);
        outState.putInt("yellowCntA", yellowCntA);
        outState.putInt("yellowCntB", yellowCntB);
        outState.putInt("redCntA", redCntA);
        outState.putInt("redCntB", redCntB);
    }

    // Restore current scores
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreCntA = savedInstanceState.getInt("scoreCntA");
        scoreCntB = savedInstanceState.getInt("scoreCntB");
        yellowCntA = savedInstanceState.getInt("yellowCntA");
        yellowCntB = savedInstanceState.getInt("yellowCntB");
        redCntA = savedInstanceState.getInt("redCntA");
        redCntB = savedInstanceState.getInt("redCntB");
        display();
    }
}
