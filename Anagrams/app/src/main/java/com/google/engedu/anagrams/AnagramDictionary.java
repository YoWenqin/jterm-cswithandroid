/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private ArrayList<String> wordList = new ArrayList<String>();

    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    //private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<String>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            //
            //  Your code here
            //
            if (!TextUtils.isEmpty(word)){
                wordSet.add(word);

                String sortedWord = sortLetters(word);
                if (lettersToWord.containsKey(sortedWord)){
                    lettersToWord.get(sortedWord).add(word);
                }
                else{
                    ArrayList<String> arr = new ArrayList<String>();
                    arr.add(word);
                    lettersToWord.put(sortedWord, arr);
                }
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        //
        // Your code here
        //
        if (wordSet.contains(word))
            if ((!word.contains(base))) {
                return true;
            }
        return false;
    }
/*
    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        //
        // Your code here
        //
        for (int i=0; i<wordList.size(); i++){
            if (wordList.get(i).length()==targetWord.length()){
                if (sortLetters(targetWord)==sortLetters(wordList.get(i))) {
                    result.add(targetWord);
                }
            }
        }
        return result;
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        //
        // Your code here
        //
        return true;
    }
*/
    @VisibleForTesting
    static String sortLetters(String input) {
        input = input.toLowerCase();
        char[] chars = input.toCharArray();
        //
        // Your code here
        //
        Arrays.sort(chars);
        String sortedWord = String.valueOf(chars);
        return sortedWord;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        //
        // Your code here
        //
        if (word!=null && !word.isEmpty()){
            for (char c = 'a'; c <= 'z'; c++){
                word += c;
                if (lettersToWord.containsKey(sortLetters(word))){
                    result.addAll(lettersToWord.get(sortLetters(word)));
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
        }
        return null;
    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
        Object[] keys = lettersToWord.keySet().toArray();
        String next = (String) keys[random.nextInt(keys.length)];
        while (lettersToWord.get(next).size() < MIN_NUM_ANAGRAMS) {
            next = (String) keys[random.nextInt(keys.length)];
        }
        ArrayList<String> words = lettersToWord.get(next);
        return words.get(random.nextInt(words.size()));
    }
}
