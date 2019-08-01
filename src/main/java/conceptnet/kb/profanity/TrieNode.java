package conceptnet.kb.profanity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * One node of a Trie.
 *
 * @author kaustubhdholé.
 */
@Getter
@Setter
@Accessors(fluent = true)
@NoArgsConstructor
public class TrieNode {

    private char c;
    private Map<Character, TrieNode> children = new HashMap<>();
    private boolean isLeaf;

    public TrieNode(char c) {
        this.c = c;
    }

}
