package com.jterm.wenqin.scarne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int userOverScore=0;
    public int userTurnScore=0;
    public int computerOverScore = 0;
    public int computerTurnScore=0;
    Random rand=new Random();

    public void ScoreDisplay(){
        userOverScore+=userTurnScore;
        computerOverScore+=computerTurnScore;
        TextView labelText = (TextView) findViewById(R.id.text1);
        labelText.setText("Your score: "+userOverScore+" Computer score:"+computerOverScore);
    }



    public void Roll(View view){
        int n = rand.nextInt(6) + 1;

        if (n==1){
            userOverScore = 0;
            ImageView diceImage = (ImageView)findViewById(R.id.diceImage);
            diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            ScoreDisplay();
        }
        else{
            userTurnScore += n;
            ScoreDisplay();
            switch(n){
                case 2:
                    ImageView diceImage = (ImageView)findViewById(R.id.diceImage);
                    diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice2));

                    break;
                case 3:
                    diceImage = (ImageView)findViewById(R.id.diceImage);
                    diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                    break;
                case 4:
                    diceImage = (ImageView)findViewById(R.id.diceImage);
                    diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                    break;
                case 5:
                    diceImage = (ImageView)findViewById(R.id.diceImage);
                    diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                    break;
                case 6:
                    diceImage = (ImageView)findViewById(R.id.diceImage);
                    diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                    break;
            }
        }
    }

    public void Reset(View view){
        userOverScore=0;
        userTurnScore=0;
        computerTurnScore=0;
        computerOverScore=0;
        ScoreDisplay();
    }

    public void Hold(View view){
        ScoreDisplay();
        userTurnScore=0;
        
    }


    public void computerTurn(){
        while(computerTurnScore<20){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
