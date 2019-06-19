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
public class CnNode extends CnObject {

    public CnNode() {
        super();
    }

    @SerializedName("@context")
    List<String> context;

    @SerializedName("edges")
    List<CnEdge> edges;

    @SerializedName("view")
    PaginationView paginationView;

    public List<String> connectedNodes() {
        return this.edges()
                .stream()
                .map(e -> (e.endNode().term().equalsIgnoreCase(id()) ? e.startNode().label() : e.endNode().label()))
                .collect(Collectors.toList());

    }

    /**
     * Return all the connected nodes in which this node appears as the object.
     *
     * @return list of phrases for each node.
     */
    public List<String> connectedSubjectNodes() {
        return edges()
                .stream()
                .filter(e -> e.objectNode().term().equalsIgnoreCase(id()))
                .map(e -> e.objectNode().label())
                .collect(Collectors.toList());

    }

    /**
     * Return all the connected nodes in which this node appears as the subject.
     *
     * @return list of phrases for each node.
     */
    public List<String> connectedObjectNodes() {
        return edges()
                .stream()
                .filter(e -> e.subjectNode().term().equalsIgnoreCase(id()))
                .map(e -> e.startNode().label())
                .collect(Collectors.toList());
    }

    public List<String> connectedSubjectNodes(List<RelationType> relationTypes) {
        return edges()
                .stream()
                .filter(e -> e.objectNode().term().equalsIgnoreCase(id()))
                .filter(e -> relationTypes.contains(e.relation().relationType()))
                .map(e -> e.objectNode().label())
                .collect(Collectors.toList());

    }

    public List<String> connectedObjectNodes(List<RelationType> relationTypes) {
        return edges()
                .stream()
                .filter(e -> e.subjectNode().term().equalsIgnoreCase(id()))
                .filter(e -> relationTypes.contains(e.relation().relationType()))
                .map(e -> e.startNode().label())
                .collect(Collectors.toList());
    }

}