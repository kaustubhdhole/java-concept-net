package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import conceptnet.kb.utilities.PaginationView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
}