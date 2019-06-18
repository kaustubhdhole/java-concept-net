package conceptnet.kb.service;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelatedTerms;

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
}
