package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import conceptnet.kb.utilities.PaginationView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A node in ConceptNet.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CnNode extends CnObject implements ICnNode {

    public CnNode() {
        super();
    }

    @SerializedName("@context")
    List<String> context;

    @SerializedName("edges")
    List<CnEdge> edges;

    @SerializedName("view")
    PaginationView paginationView;

    /**
     * /c/en/bank_account
     *
     * @return bank account
     */
    public String label() {
        return this.id().substring(6).replace("_", " ");
    }

    @Override
    public List<String> connectedNodes() {
        return this.edges()
                .stream()
                .map(e -> (e.endNode().term().equalsIgnoreCase(id()) ? e.startNode() : e.endNode()))
                .filter(node -> node.language() != null && node.language().equalsIgnoreCase("en"))
                .map(node -> node.label())
                .collect(Collectors.toList());

    }


    @Override
    public List<String> connectedSubjectNodes() {
        return edges()
                .stream()
                .filter(e -> e.objectNode().term().equalsIgnoreCase(id()))
                .filter(e -> e.subjectNode().language() != null && e.subjectNode().language().equalsIgnoreCase("en"))
                .map(e -> e.subjectNode().label())
                .collect(Collectors.toList());

    }


    @Override
    public List<String> connectedObjectNodes() {
        return edges()
                .stream()
                .filter(e -> e.subjectNode().term().equalsIgnoreCase(id()))
                .filter(e -> e.objectNode().language() != null && e.objectNode().language().equalsIgnoreCase("en"))
                .map(e -> e.objectNode().label())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> connectedSubjectNodes(List<RelationType> relationTypes) {
        return edges()
                .stream()
                .filter(e -> e.objectNode().term().equalsIgnoreCase(id()))
                .filter(e -> relationTypes.contains(e.relation().relationType()))
                .filter(e -> e.subjectNode().language() != null && e.subjectNode().language().equalsIgnoreCase("en"))
                .map(e -> e.subjectNode().label())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> connectedObjectNodes(List<RelationType> relationTypes) {
        return edges()
                .stream()
                .filter(e -> e.subjectNode().term().equalsIgnoreCase(id()))
                .filter(e -> relationTypes.contains(e.relation().relationType()))
                .filter(e -> e.objectNode().language() != null && e.objectNode().language().equalsIgnoreCase("en"))
                .map(e -> e.objectNode().label())
                .collect(Collectors.toList());
    }


    @Override
    public boolean isConnectedTo(List<String> thoseNodes) {
        return connectedNodes()
                .stream()
                .anyMatch(thisNode -> thoseNodes.contains(thisNode));
    }


    @Override
    public long numberOfConnections(List<String> thoseNodes) {
        return connectedNodes()
                .stream()
                .filter(thisNode -> thoseNodes.contains(thisNode))
                .count();
    }


    @Override
    public boolean unigramOverlap(List<String> thoseNodes) {
        if (isConnectedTo(thoseNodes)) {
            return true;
        } else {
            return connectedNodes()
                    .stream()
                    .anyMatch(thisNode -> {
                        if (thisNode.contains(" ")) {
                            for (String word : thisNode.split(" ")) {
                                if (thoseNodes.contains(word)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    });
        }
    }


    @Override
    public boolean anyMatch(List<CnNode> thoseNodes) {
        return connectedNodes()
                .stream()
                .anyMatch(thisNode -> thoseNodes
                        .stream()
                        .map(n -> n.label())
                        .collect(Collectors.toList())
                        .contains(thisNode));
    }
}