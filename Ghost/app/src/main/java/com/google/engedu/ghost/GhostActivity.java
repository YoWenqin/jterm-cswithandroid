/* Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static java.io.FileDescriptor.out;


public class GhostActivity extends AppCompatActivity {
    private static final String TAG = "GhostActivity";

    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private static final String KEY_USER_TURN = "keyUserTurn";
    private static final String KEY_CURRENT_WORD = "keyCurrentWord";
    private static final String KEY_SAVED_STATUS = "keySavedStatus";
    private static final String KEY_BUTTON_STATUS = "keyButtonStatus";

    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private String currentWord = "";
    private String status;
    private boolean buttonStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            // Initialize your dictionary from the InputStream.
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }

        if (savedInstanceState!=null){
            userTurn = savedInstanceState.getBoolean(KEY_USER_TURN);
            currentWord = savedInstanceState.getString(KEY_CURRENT_WORD);
            status = savedInstanceState.getString(KEY_SAVED_STATUS);
            buttonStatus = savedInstanceState.getBoolean(KEY_BUTTON_STATUS);
            TextView label = (TextView) findViewById(R.id.gameStatus);
            TextView text = (TextView) findViewById(R.id.ghostText);
            Button challengeButton = (Button) findViewById(R.id.challengeButton);
            text.setText(currentWord);
            label.setText(status);
            challengeButton.setEnabled(buttonStatus);
        }
        else{
            onStart(null);
        }
    }

    protected void onSaveInstanceState(Bundle outState){
        outState.putBoolean(KEY_USER_TURN,userTurn);
        outState.putString(KEY_CURRENT_WORD,currentWord);
        outState.putString(KEY_SAVED_STATUS, status);
        outState.putBoolean(KEY_BUTTON_STATUS, buttonStatus);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char pressedKey = (char)event.getUnicodeChar();
        pressedKey = Character.toLowerCase(pressedKey);

        if (!(('a'<=pressedKey) && (pressedKey<='z'))){
            Log.d("onKeyUp",Integer.toString(pressedKey));
            return super.onKeyUp(keyCode, event);
        }
        else{
            currentWord+=pressedKey;
            TextView text = (TextView) findViewById(R.id.ghostText);
            text.setText(currentWord);
            Log.d("onKeyUp","Add a letter to current word");
            computerTurn();
            return true;
        }
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */


    public boolean onStart(View view) {
        currentWord="";
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        Button challengeButton = (Button) findViewById(R.id.challengeButton);
        buttonStatus=true;
        challengeButton.setEnabled(buttonStatus);
        if (userTurn) {
            status = USER_TURN;
            label.setText(status);
        } else {
            status = COMPUTER_TURN;
            label.setText(status);
            computerTurn();
        }
        return true;
    }

    public void Challenge(View view){
        TextView label = (TextView) findViewById(R.id.gameStatus);
        Button challengeButton = (Button) findViewById(R.id.challengeButton);
        if (currentWord.length()>=4 && dictionary.isWord(currentWord)){
            status = "User wins!!!";
            label.setText(status);
        }
        else{
            String tryWord= dictionary.getAnyWordStartingWith(currentWord);
            if (!tryWord.equals(null)){
                status = "Computer wins!!! "+tryWord;
                label.setText(status);
            }
            else{
                status = "User wins!!!";
                label.setText(status);
            }
        }
        buttonStatus=false;
        challengeButton.setEnabled(buttonStatus);
    }

    public void Restart(View view){
        onStart(view);
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);


        if (currentWord.length()>=4 && dictionary.isWord(currentWord)){
            status="Computer wins!!!";
            label.setText(status);
            Log.d("computerTurn","userWord is valid ");
            //onStart(null);
            return;
        }
        else{
            String tryWord= dictionary.getAnyWordStartingWith(currentWord);
            if (tryWord==null){
                Log.d("computerTurn","tryWord is null");
                status="Computer wins!!!";
                label.setText(status);
                //onStart(null);
                return;
            }
            else{
                currentWord=tryWord.substring(0,currentWord.length()+1);
                text.setText(currentWord);
            }
        }

        // Do computer turn stuff then make it the user's turn again
        userTurn = true;
        label.setText(USER_TURN);
    }
}
