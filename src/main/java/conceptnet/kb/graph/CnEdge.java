package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import conceptnet.kb.utilities.Source;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * An edge, or assertion - unit of knowledge in ConceptNet. It tells us a particular relation between natural-language terms, according to a particular source.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CnEdge extends CnObject {

    public CnEdge() {
        super();
    }

    @SerializedName("dataset")
    String dataSet;

    /**
     * The second argument of the assertion.
     */
    @SerializedName("end")
    ConnectedNode endNode;

    @SerializedName("license")
    String license;

    /**
     * The predicate (relation) of this assertion.
     */
    @SerializedName("rel")
    CnRelation relation;

    @SerializedName("sources")
    List<Source> sources;

    /**
     * The first argument of the assertion.
     */
    @SerializedName("start")
    ConnectedNode startNode;

    /**
     * The original natural language text that expressed this statement.
     * May be null, because not every statement was derived from natural language input.
     * The locations of the start and end concepts will be marked by surrounding them with double brackets.
     * An example of a surfaceText is "a cat is an animal".
     */
    @SerializedName("surfaceText")
    String surfaceText;

    /**
     * The strength with which this edge expresses this assertion.
     * A typical weight is 1, but weights can be higher or lower. All weights are positive.
     */
    @SerializedName("weight")
    float weight;

}