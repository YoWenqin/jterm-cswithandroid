package com.jterm.wenqin.scarne;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public int userOverScore=0;
    public int userTurnScore=0;
    public int computerOverScore = 0;
    public int computerTurnScore=0;
    public boolean userTurn=true;
    Random rand=new Random();
    private static final String TAG = MainActivity.class.getSimpleName();

    public void OverScoreDisplay(){
        TextView labelText = (TextView) findViewById(R.id.text1);
        labelText.setText("Your overall score: "+userOverScore+" Computer overall score: "+computerOverScore);
    }

    public void TurnScoreDisplay(){
        TextView labelText = (TextView) findViewById(R.id.text2);
        Log.d("UserTurnScore", String.valueOf(userTurnScore));
        labelText.setText("Your turn score: "+userTurnScore+" Computer turn score: "+computerTurnScore);
    }

    public void diceDisplay(ImageView diceImage, int n){
        switch(n){
            case 1:
                Log.d(TAG, "Display dice1 here");
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                diceImage.setContentDescription("Roll 1");
                break;
            case 2:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                diceImage.setContentDescription("Roll 2");
                break;
            case 3:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                diceImage.setContentDescription("Roll 3");
                break;
            case 4:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                diceImage.setContentDescription("Roll 4");
                break;
            case 5:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                diceImage.setContentDescription("Roll 5");
                break;
            case 6:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                diceImage.setContentDescription("Roll 6");
                break;
        }
    }

    public void Reset(View view){
        mHandler.removeCallbacks(mRunnable);
        userOverScore=0;
        userTurnScore=0;
        computerTurnScore=0;
        computerOverScore=0;
        TurnScoreDisplay();
        OverScoreDisplay();
        userTurn=true;
        Button rollButton = (Button)findViewById(R.id.rollButton);
        rollButton.setEnabled(true);
        Button holdButton = (Button)findViewById(R.id.holdButton);
        holdButton.setEnabled(true);
    }

    public void userRoll(View view){
        rollDice();
        }

    public void rollDice(){
        Log.d(TAG, "Dice rolled here");
        int n = rand.nextInt(6) + 1;

        if (userTurn==true){
            if (n==1){
                userTurnScore=0;
                userOverScore=0;
            }
            else{
                userTurnScore += n;
            }
        }
        //computer's turn:
        else{
            if (n==1){
                Log.d("ComputerTurnScore", String.valueOf(computerTurnScore));
                computerTurnScore= 0;
                computerOverScore = 0;
                mHandler.removeCallbacks(mRunnable);
            }
            else{
                computerTurnScore += n;
            }
        }

        ImageView diceImage = (ImageView)findViewById(R.id.diceImage);
        TurnScoreDisplay();
        diceDisplay(diceImage, n);
        if (n==1){
            holdDice();
        }

    }

    public void userHold(View view){
        holdDice();
    }

    public void holdDice(){
        Log.d(TAG, "Dice held here");
        if (userTurn==true){
            userOverScore+=userTurnScore;
            userTurnScore=0;
        }
        else{
            computerOverScore+=computerTurnScore;
            computerTurnScore=0;
        }
        OverScoreDisplay();
        TurnScoreDisplay();

        userTurn = !userTurn;
        Button rollButton = (Button)findViewById(R.id.rollButton);
        rollButton.setEnabled(userTurn);
        Button holdButton = (Button)findViewById(R.id.holdButton);
        holdButton.setEnabled(userTurn);

        if (userTurn==true) {
            Log.d(TAG, "User turn switched on");
        }
        else {
            Log.d(TAG, "User turn switched off");
            computerTurn();
        }
    }

    final Handler mHandler = new Handler();
    final Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            Log.d(TAG, "Computer rolls dice");
            if (computerTurnScore<20){
                mHandler.postDelayed(this, 2000);
                rollDice();
            }
            else{
                Log.d(TAG, "ComputerTurnScore>=20");
                holdDice();
            }
        }
    };

    public void computerTurn(){
        mHandler.postDelayed(mRunnable,2000);
        //mHandler.removeCallbacks(mRunnable);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
