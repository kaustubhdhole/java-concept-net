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

import java.util.Map;

/**
 * A complete Trie.
 *
 * @author kaustubhdholé.
 */
public class Trie implements ITrie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    @Override
    public void insert(String word) {
        Map<Character, TrieNode> children = root.children();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node;
            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new TrieNode(c);
                children.put(c, node);
            }
            children = node.children();

            if (i == word.length() - 1) {
                node.isLeaf(true);
            }
        }
    }

    @Override
    public boolean search(String word) {
        Map<Character, TrieNode> children = root.children();
        TrieNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (children.containsKey(c)) {
                node = children.get(c);
                children = node.children();
            } else {
                node = null;
                break;
            }
        }
        return node != null && node.isLeaf();
    }
}
