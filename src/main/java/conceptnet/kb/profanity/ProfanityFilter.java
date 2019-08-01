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
            System.err.println("Preposition file not loaded");
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
