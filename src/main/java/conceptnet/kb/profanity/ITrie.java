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
