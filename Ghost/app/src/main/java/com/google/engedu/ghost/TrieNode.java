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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;
    private Random mRandom;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        mRandom = new Random();
    }

    public void add(String s) {
        if (s.length()==0){
            isWord = true;
            return;
        }

        String firstChar = s.substring(0,1);
        String remaining = s.substring(1);
        if (children.containsKey(firstChar)){
            // If the character is already in my children map
            // Then I can ask the child at that character to add the remaining string suffix
            children.get(firstChar).add(remaining);
        } else {
            // If it doesn't exist in the children, so I'll need to insert it into the character map
            TrieNode newNode = new TrieNode();
            children.put(firstChar,newNode);
        }
    }

    public boolean isWord(String s) {
        if (s.length()==0){
            return isWord;
        }

        //Find out if we have a child with s prefix and that child.isWord(s's suffix)
        String firstChar = s.substring(0,1);
        String remaining = s.substring(1);
        if (children.containsKey(firstChar)){
            return children.get(firstChar).isWord(remaining);
        }
        //The prefix is not in the children list, so this is not a valid word
        return false;
    }

    public String getAnyWordStartingWith(String s) {
        /*
        if (s == null){
            if (children.size()>0)
        }
        if (s.length()==0){
            //If there is no prefix, return a random word
            //If we have no children, we can return the empty string if we are a word, or null
            if (children.size()==0){
                if (isWord){
                    return "";
                } else {
                    return null;
                }
            } else if (children.size()>0){
            String childKey = pickRandomChildChar();
            String nextRemaining = children.get(childKey).getAnyWordStartingWith("");
            return childKey + nextRemaining;}
        } else {
            // s is not empty
            // return a word that starts with the first letter of s
            String firstChar = s.substring(0,1);
            String remaining = s.substring(1);
            if (children.containsKey(firstChar)){
                String word = children.get(firstChar).getAnyWordStartingWith(remaining);
                if (word==null){
                    return null;
                } else {
                    return firstChar + word;
                }
            }
        }
        return null;
        */
        if (s.length()==0){
            return pickRandomChildChar();
        } else{
            if (!children.containsKey(s.substring(0,1))){
                return null;
            } else {
                String newWord = children.get(s.substring(0,1)).getAnyWordStartingWith(s.substring(1));
                if (newWord==null){
                    return null;
                } else {
                    return s.substring(0,1) + newWord;
                }
            }
        }
    }

    private String pickRandomChildChar() {
        int index = mRandom.nextInt(children.size());
        int reached = 0;
        for (String s :children.keySet()){
            if (index == reached){
                return s;
            }
            reached++;
        }
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}