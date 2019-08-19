package conceptnet.kb.wordnet;

/**
 * Either a noun or a verb.
 *
 * @author kaustubhdholé.
 */
public enum PosType {
    Noun,
    Verb,
    Adverb,
    Adjective,
    None;

    public static PosType fromString(String pos) {
        switch (pos) {
            case "a":
                return Adjective;
            case "v":
                return Verb;
            case "r":
                return Adverb;
            case "n":
                return Noun;
            default:
                return None;
        }
    }
}
