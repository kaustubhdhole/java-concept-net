package conceptnet.kb.utilities;

/**
 * ConceptNet API exceptions when nodes are not found.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetApiException extends Exception {

    public ConceptNetApiException(String message) {
        super(message);
    }
}
