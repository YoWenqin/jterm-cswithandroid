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

package com.google.engedu.worldladder;

import com.google.engedu.wordladder.PathDictionary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class PathDictionaryTest {
    private String[] wordsArray = {"can", "cap", "cat", "dig", "dot", "dog", "fire", "gain", "gait",
            "wait", "ware", "wart", "wire"};

    @Test
    public void testIsWord() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(wordsArray));
        PathDictionary dict = new PathDictionary(words);
        assertTrue(dict.isWord("cat"));
        assertFalse(dict.isWord("tac"));
    }

    @Test
    public void testNeighbors() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(wordsArray));
        PathDictionary dict = new PathDictionary(words);

    }
}