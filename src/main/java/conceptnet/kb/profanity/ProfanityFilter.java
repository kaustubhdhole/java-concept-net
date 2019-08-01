/*
 *
 *  * Copyright 2019 Kaustubh Dhole
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package conceptnet.kb.profanity;

import conceptnet.kb.graph.ConnectedNode;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

/**
 * Determine if a concept in ConceptNet is profane.
 * <p>
 * Lexicon taken from https://github.com/RobertJGabriel/Google-profanity-words
 *
 * @author kaustubhdholé.
 */
public class ProfanityFilter implements Predicate<ConnectedNode> {

    private Trie profaneLexicon;

    public ProfanityFilter() {
        profaneLexicon = new Trie();
        try {
            @Cleanup BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(
                            "src/main/resources/conceptnet/kb/profanity/profane_words.txt")));
            br.lines().map(String::trim)
                    .forEach(word -> profaneLexicon.insert(word));
        } catch (IOException e) {
            System.err.println("Profanity file not loaded");
        }
    }

    @Override
    public boolean test(ConnectedNode node) {
        String[] unigrams = node.label().split(" ");
        for (String unigram : unigrams) {
            if (profaneLexicon.search(unigram)) {
                return false;
            }
        }
        return !profaneLexicon.search(node.label());
    }
}
