package conceptnet.kb.service;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;
import conceptnet.kb.utilities.CnEdgeApi;
import conceptnet.kb.utilities.CnNodeApi;
import conceptnet.kb.utilities.CnRelatedTermsApi;

import java.util.Optional;

/**
 * Services to query ConceptNet API.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetService implements KnowledgeBaseService {

    @Override
    public Optional<CnNode> query(String phrase) {
        return CnNodeApi.query(phrase);
    }

    @Override
    public Optional<RelatedTerms> getRelatedTerms(String phrase) {
        return CnRelatedTermsApi.getRelatedTerms(phrase);
    }

    @Override
    public Optional<CnEdge> getEdge(String edgeUri) {
        return CnEdgeApi.query(edgeUri);
    }

    public static void main(String[] args) {
        KnowledgeBaseService knowledgeBaseService = new ConceptNetService();
        Optional<CnNode> response = knowledgeBaseService.query("credit_card");
        Optional<RelatedTerms> relatedTerms = knowledgeBaseService.getRelatedTerms("tea madness people");
        Optional<CnEdge> edge = knowledgeBaseService.getEdge("[/r/Synonym/,/c/pt/rabanada/n/wn/food/,/c/en/french_toast/n/wn/food/]");
        System.out.println();
        if (response.isPresent()) {
            CnNode node = response.get();
            System.out.println("Node found: " + node.id() + "\n");
            node.edges()
                    .stream()
                    .filter(e -> e.relation().relationType().equals(RelationType.CapableOf))
                    .forEach(e -> System.out.println(e.startNode().label() + " CapableOf " + e.endNode().label()));
            System.out.println();
        } else {
            System.out.println("No node found.");
        }
    }

}
