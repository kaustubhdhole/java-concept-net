package conceptnet.kb.service;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;

import java.util.List;
import java.util.Optional;

/**
 * Service that can interface with an external KB.
 *
 * @author kaustubhdholé.
 */
public interface KnowledgeBaseService {

    Optional<CnNode> query(String phrase);

    Optional<RelatedTerms> getRelatedTerms(String phrase);

    Optional<CnEdge> getEdge(String edgeUri);

    List<Integer> distanceBetween(CnNode node1, CnNode node2, List<RelationType> relationTypes);

    List<String> getHyponyms(String phrase);
}