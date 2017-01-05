package com.jterm.wenqin.scarne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int userOverScore;
    public int userTurnScore;
    public int computerOverScore = 0;
    public int computerTurnScore;
    Random rand=new Random();

    public void ScoreDisplay(){
        TextView labelText = (TextView) findViewById(R.id.text1);
        labelText.setText("Your score: "+userOverScore+" Computer score:"+computerOverScore);
    }

    public void Roll(View view){
        userTurnScore = rand.nextInt(6) + 1;

        if (userTurnScore==1){
            userOverScore = 0;
            ImageView diceImage = (ImageView)findViewById(R.id.diceImage);
            diceImage.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            ScoreDisplay();

        }
        else{
            userOverScore += userTurnScore;
            ScoreDisplay();
            switch(userTurnScore){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
