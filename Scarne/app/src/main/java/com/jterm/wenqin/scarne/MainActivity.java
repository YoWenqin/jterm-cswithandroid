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
        userOverScore+=userTurnScore;
        computerOverScore+=computerTurnScore;
        TextView labelText = (TextView) findViewById(R.id.text1);
        labelText.setText("Your overall score: "+userOverScore+" Computer overall score: "+computerOverScore);
    }

    public void TurnScoreDisplay(){
        TextView labelText = (TextView) findViewById(R.id.text2);
        Log.d("UserTurnScore:", String.valueOf(userTurnScore));
        labelText.setText("Your turn score: "+userTurnScore+" Computer turn score: "+computerTurnScore);
    }
    public void userRoll(View view){
        rollDice();
        }

    public void diceDisplay(ImageView diceImage, int n){
        switch(n){
            case 1:
                Log.d(TAG, "Display dice1 here");
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:
                diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                break;
        }
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
                computerTurnScore=0;
                computerOverScore = 0;
            }
            else{
                computerTurnScore += n;
            }
        }

        ImageView diceImage = (ImageView)findViewById(R.id.diceImage);
        if (n==1){
            Log.d("UpdateTurnScore" , String.valueOf(userTurnScore));
            TurnScoreDisplay();
            diceDisplay(diceImage,n);
            holdDice();
        }
        else{
            TurnScoreDisplay();
            diceDisplay(diceImage,n);
        }

    }

    public void Reset(View view){
        userOverScore=0;
        userTurnScore=0;
        computerTurnScore=0;
        computerOverScore=0;
        TurnScoreDisplay();
        OverScoreDisplay();
    }

    public void userHold(View view){

        holdDice();
    }

    public void holdDice(){
        Log.d(TAG, "Dice held here");
        OverScoreDisplay();


        userTurn = !userTurn;
        Button rollButton = (Button)findViewById(R.id.rollButton);
        rollButton.setEnabled(userTurn);
        Button holdButton = (Button)findViewById(R.id.holdButton);
        holdButton.setEnabled(userTurn);
        Log.d("userTurn", String.valueOf(userTurn));

        if (userTurn==true) {

            Log.d(TAG, "User turn switched on");
        }
        else {
            Log.d(TAG, "User turn switched off");
            computerTurn();
        }

    }


    public void computerTurn(){
        final Handler mHandler = new Handler();
        final Runnable mRunnable = new Runnable(){
            @Override
            public void run() {
                Log.d(TAG, "Computer rolls dice");
                if (computerTurnScore<20){
                rollDice();
                mHandler.postDelayed(this, 1000);
            }}
        };

        mHandler.postDelayed(mRunnable,1000);


        if (computerTurnScore >= 20){

            holdDice();
            Log.d(TAG, "RemoveCallBacks for handler");
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
