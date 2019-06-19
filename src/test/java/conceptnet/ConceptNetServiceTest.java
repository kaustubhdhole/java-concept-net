package conceptnet;

import conceptnet.kb.graph.CnNode;
import conceptnet.kb.service.ConceptNetService;
import conceptnet.kb.service.KnowledgeBaseService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test cases for querying ConceptNet API.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetServiceTest {

    private static KnowledgeBaseService knowledgeBaseService;

    static List<String> domainFilter = new ArrayList<>();

    static String searchWord = "money transfer";

    @BeforeClass
    public static void load() {
        knowledgeBaseService = new ConceptNetService();
        domainFilter.add("bank");
        domainFilter.add("banking");
        domainFilter.add("finance");
    }

    @Test
    public void testRelatedTerms() {
        Optional<CnNode> cnNodeOptional = knowledgeBaseService.query(searchWord);
        if (cnNodeOptional.isPresent()) {
            CnNode cnNode = cnNodeOptional.get();
            boolean isDirectlyConnected = cnNode.isConnectedTo(domainFilter);
            System.out.println(isDirectlyConnected);
            boolean unigramOverlap = cnNode.unigramOverlap(domainFilter);
            System.out.println(unigramOverlap);
        }
    }

    @Test
    public void testHyponyms() {
        List<String> cnNodeOptional = knowledgeBaseService.getHyponyms(searchWord);
        System.out.println("\nHyponyms\n");
        cnNodeOptional.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void domainFilteredHyponyms() {
        String searchWord = "money transfer";
        System.out.println(searchWord);
        List<String> hyponyms = knowledgeBaseService.getHyponyms(searchWord);
        System.out.println("\nDomain filtered Hyponyms\n");
        hyponyms.stream()
                .map(h -> knowledgeBaseService.query(h).get())
                .filter(h -> h.unigramOverlap(domainFilter))
                .forEach(h -> System.out.println(h.label()));
    }
}
