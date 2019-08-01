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
