package conceptnet.kb.service;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;
import conceptnet.kb.utilities.CnEdgeApi;
import conceptnet.kb.utilities.CnNodeApi;
import conceptnet.kb.utilities.CnRelatedTermsApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    private List<String> getHypoNyms(String phrase) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return n.edges().stream()
                    .filter(r -> r.relation().relationType().equals(RelationType.IsA) && r.endNode().term().equalsIgnoreCase(n.id()))
                    .map(r -> r.startNode().label())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    @Override
    public Optional<CnEdge> getEdge(String edgeUri) {
        return CnEdgeApi.query(edgeUri);
    }

    @Override
    public List<Integer> distanceBetween(CnNode node1, CnNode node2, List<RelationType> relationTypes) {
        return null;
    }

    public static void main(String[] args) {
        ConceptNetService knowledgeBaseService = new ConceptNetService();
        List<String> domainFilter = new ArrayList<>();
        domainFilter.add("bank");
        domainFilter.add("banking");
        domainFilter.add("finance");
        String searchWord = "Bank of America";
        Optional<CnNode> cnNodeOptional = knowledgeBaseService.query(searchWord);
        if (cnNodeOptional.isPresent()) {
            CnNode cnNode = cnNodeOptional.get();
            boolean unigramOverlap = cnNode.unigramOverlap(domainFilter);
            System.out.println(unigramOverlap);
        }
    }

}
