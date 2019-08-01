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

/**
 * Default functions useful for working with a trie.
 *
 * @author kaustubhdholé.
 */
public interface ITrie {

    /**
     * Insert a word in the trie.
     *
     * @param word "good"
     */
    void insert(String word);

    /**
     * Return true if the trie is present.
     *
     * @param word "bad"
     * @return false
     */
    boolean search(String word);
}
