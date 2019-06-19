package conceptnet.kb.graph;

import java.util.List;

/**
 * ConceptNet Node Service.
 *
 * @author kaustubhdholé.
 */
public interface ICnNode {

    /**
     * Return all nodes connected one-hop away.
     *
     * @return display term for each node
     */
    List<String> connectedNodes();

    /**
     * Return all the connected nodes in which this node appears as the object.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedSubjectNodes();

    /**
     * Return all the connected nodes in which this node appears as the subject.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedObjectNodes();

    /**
     * Return all the nodes connected with one of the relationTypes
     * in which this node appears as the object.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedSubjectNodes(List<RelationType> relationTypes);

    /**
     * Return all the nodes connected with one of the relationTypes
     * in which this node appears as the subject.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedObjectNodes(List<RelationType> relationTypes);

    /**
     * If thisNode is connected to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean isConnectedTo(List<String> thoseNodes);

    /**
     * If thisNode is connected to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    long numberOfConnections(List<String> thoseNodes);

    /**
     * If thisNode is loosely related to any of the mentioned thoseNodes.
     * Here, the node is checked not only for an exact match but even for a partial 1-gram overlap.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean unigramOverlap(List<String> thoseNodes);

    /**
     * If thisNode has is related to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean anyMatch(List<CnNode> thoseNodes);
}
